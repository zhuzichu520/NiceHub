package com.zhuzichu.android.widget.notify

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.VisibleForTesting
import androidx.core.app.NotificationCompat
import androidx.core.app.Person
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import java.util.*
import kotlin.collections.ArrayList

class NotifyManager constructor(private val context: Context) {

    companion object {
        const val CHANNEL_DEFAULT_KEY = "application_notification"

        const val CHANNEL_DEFAULT_NAME = "Application notifications."

        const val CHANNEL_DEFAULT_DESCRIPTION = "General application notifications."

        const val IMPORTANCE_MIN = NotificationCompat.PRIORITY_MIN

        const val IMPORTANCE_LOW = NotificationCompat.PRIORITY_LOW

        const val IMPORTANCE_NORMAL = NotificationCompat.PRIORITY_DEFAULT

        const val IMPORTANCE_HIGH = NotificationCompat.PRIORITY_HIGH

        const val IMPORTANCE_MAX = NotificationCompat.PRIORITY_MAX

        const val NO_LIGHTS = 0

        internal var defaultConfig = NotifyConfig()
    }

    private val notificationManager: NotificationManager? by lazy {
        ContextCompat.getSystemService(context, NotificationManager::class.java)
    }

    fun getCreator(): NotifyCreator = NotifyCreator(this)

    fun asBuilder(payload: RawNotification): NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(context, payload.alerting.channelKey)
            .extend(NotifyExtender())
            .setColor(payload.header.color)
            .setSmallIcon(payload.header.icon)
            .setSubText(payload.header.headerText)
            .setShowWhen(payload.header.showTimestamp)
            .setAutoCancel(payload.meta.cancelOnClick)
            .setContentIntent(payload.meta.clickIntent)
            .setDeleteIntent(payload.meta.clearIntent)
            .setCategory(payload.meta.category)
            .setLocalOnly(payload.meta.localOnly)
            .setOngoing(payload.meta.sticky)
            .setTimeoutAfter(payload.meta.timeout)
        payload.meta.contacts.takeIf { it.isNotEmpty() }?.forEach {
            builder.addPerson(it)
        }
        if (payload.content is Payload.Content.Standard) {
            builder.setContentTitle(payload.content.title)
                .setContentText(payload.content.text)
        }
        if (payload.content is Payload.Content.SupportsLargeIcon) {
            builder.setLargeIcon(payload.content.largeIcon)
        }
        payload.actions?.forEach {
            builder.addAction(it)
        }
        payload.alerting.apply {
            notificationChannelInterop(this)
            builder.setVisibility(lockScreenVisibility)
            if (lightColor != NO_LIGHTS) {
                builder.setLights(lightColor, 500, 2000)
            }
            builder.priority = channelImportance
            if (channelImportance >= IMPORTANCE_NORMAL) {
                vibrationPattern
                    .takeIf { it.isNotEmpty() }
                    ?.also {
                        builder.setVibrate(it.toLongArray())
                    }
                builder.setSound(sound)
            }
        }
        var style: NotificationCompat.Style? = null
        payload.stackable?.let {
            builder.extend(
                NotifyExtender()
                    .setKey(it.key)
                    .setStackable(true)
                    .setSummaryText(it.summaryContent)
            )

            val activeNotifications =
                getActiveNotifications()
            if (!activeNotifications.isNullOrEmpty()) {
                style = buildStackedNotification(
                    activeNotifications,
                    builder,
                    payload
                )
            }
        }
        if (style == null) {
            style = setStyle(builder, payload.content)
        }
        builder.setStyle(style)
        return builder
    }


    private fun notificationChannelInterop(alerting: Payload.Alerts): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return false
        }
        notificationManager?.getNotificationChannel(alerting.channelKey)?.run {
            return true
        }
        val channel = NotificationChannel(
            alerting.channelKey,
            alerting.channelName,
            alerting.channelImportance + 3
        ).apply {
            description = alerting.channelDescription
            lockscreenVisibility = alerting.lockScreenVisibility
            alerting.lightColor
                .takeIf { it != NO_LIGHTS }
                ?.let {
                    enableLights(true)
                    lightColor = alerting.lightColor
                }
            alerting.vibrationPattern.takeIf { it.isNotEmpty() }?.also {
                enableVibration(true)
                vibrationPattern = it.toLongArray()
            }
            alerting.sound.also {
                setSound(it, android.media.AudioAttributes.Builder().build())
            }
            Unit
        }
        notificationManager?.createNotificationChannel(channel)
        return true
    }


    @SuppressLint("ObsoleteSdkInt")
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun getActiveNotifications(): List<NotifyExtender>? {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return ArrayList()
        }
        return notificationManager?.activeNotifications
            ?.map { NotifyExtender(it) }
            ?.filter { it.valid }
    }

    private fun buildStackedNotification(
        groupedNotifications: List<NotifyExtender>,
        builder: NotificationCompat.Builder,
        payload: RawNotification
    ): NotificationCompat.InboxStyle? {
        if (payload.stackable == null) {
            return null
        }
        val lines: ArrayList<CharSequence> = ArrayList()
        groupedNotifications
            .filter { it.stackable }
            .filter { it.stackKey == payload.stackable.key }
            .forEach { item ->
                if (item.stacked) {
                    item.stackItems?.forEach { lines.add(it.toString()) }
                } else {
                    item.summaryContent?.let { lines.add(it) }
                }
            }
        if (lines.size == 0) return null
        lines.add(payload.stackable.summaryContent.toString())
        val style = NotificationCompat.InboxStyle()
            .setBigContentTitle(payload.stackable.summaryTitle?.invoke(lines.size))
            .also { style ->
                lines.forEach { style.addLine(it) }
            }
        builder.setStyle(style)
            .setContentTitle(payload.stackable.summaryTitle?.invoke(lines.size))
            .setContentText(
                getAsSecondaryFormattedText(
                    payload.stackable.summaryDescription?.invoke(
                        lines.size
                    )
                )
            )
            .setContentIntent(payload.stackable.clickIntent)
            .extend(
                NotifyExtender().setStacked(true)
            )
        builder.mActions.clear()
        payload.stackable.stackableActions?.forEach {
            builder.addAction(it)
        }
        return style
    }


    private fun setStyle(
        builder: NotificationCompat.Builder,
        content: Payload.Content
    ): NotificationCompat.Style? {
        return when (content) {
            is Payload.Content.Default -> {
                null
            }
            is Payload.Content.TextList -> {
                NotificationCompat.InboxStyle().also { style ->
                    content.lines.forEach { style.addLine(it) }
                }
            }
            is Payload.Content.BigText -> {
                builder.setContentText(
                    getAsSecondaryFormattedText(
                        (content.text
                            ?: "").toString()
                    )
                )
                val bigText: CharSequence = HtmlCompat.fromHtml(
                    "<font color='#3D3D3D'>" + (content.expandedText
                        ?: content.title) + "</font><br>" + content.bigText?.replace(
                        "\n".toRegex(),
                        "<br>"
                    ), HtmlCompat.FROM_HTML_MODE_LEGACY
                )
                NotificationCompat.BigTextStyle()
                    .bigText(bigText)
            }
            is Payload.Content.BigPicture -> {
                NotificationCompat.BigPictureStyle()
                    .setSummaryText(content.expandedText ?: content.text)
                    .bigPicture(content.image)
                    .bigLargeIcon(null)

            }
            is Payload.Content.Message -> {
                NotificationCompat.MessagingStyle(Person.Builder().setName(content.userDisplayName).build())
                    .setConversationTitle(content.conversationTitle)
                    .also { s ->
                        content.messages.forEach { s.addMessage(it.text, it.timestamp, it.person) }
                    }
            }
        }
    }

    fun cancelNotification(id: Int) {
        notificationManager?.cancel(id)
    }

    fun show(i: Int?, notification: NotificationCompat.Builder): Int {
        val key = NotifyExtender.getKey(notification.extras)
        var id = i ?: getRandomInt()
        if (key != null) {
            id = key.hashCode()
            notificationManager?.notify(key.toString(), id, notification.build())
        } else {
            notificationManager?.notify(id, notification.build())
        }
        return id
    }

    private fun getRandomInt(): Int {
        return Random().nextInt(Int.MAX_VALUE - 100) + 100
    }

    private fun getAsSecondaryFormattedText(str: String?): CharSequence? {
        str ?: return null
        return HtmlCompat.fromHtml(
            "<font color='#3D3D3D'>$str</font>",
            HtmlCompat.FROM_HTML_MODE_LEGACY
        )
    }
}