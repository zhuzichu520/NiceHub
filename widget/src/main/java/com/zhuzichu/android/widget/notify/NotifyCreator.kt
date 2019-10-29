package com.zhuzichu.android.widget.notify

import androidx.core.app.NotificationCompat

class NotifyCreator constructor(private val notifyManager: NotifyManager) {

    private var meta = Payload.Meta()
    private var content: Payload.Content = Payload.Content.Default()
    private var alerts = NotifyManager.defaultConfig.defaultAlerting
    private var header = NotifyManager.defaultConfig.defaultHeader.copy()
    private var actions: ArrayList<Action>? = null
    private var stackable: Payload.Stackable? = null

    fun meta(init: Payload.Meta.() -> Unit): NotifyCreator {
        this.meta.init()
        return this
    }

    fun alerting(key: String, init: Payload.Alerts.() -> Unit): NotifyCreator {
        this.alerts = this.alerts.copy(channelKey = key)
        this.alerts.init()
        return this
    }

    fun content(init: Payload.Content.Default.() -> Unit): NotifyCreator {
        this.content = Payload.Content.Default()
        (this.content as Payload.Content.Default).init()
        return this
    }

    fun header(init: Payload.Header.() -> Unit): NotifyCreator {
        this.header.init()
        return this
    }

    fun asTextList(init: Payload.Content.TextList.() -> Unit): NotifyCreator {
        this.content = Payload.Content.TextList()
        (this.content as Payload.Content.TextList).init()
        return this
    }

    fun asBigText(init: Payload.Content.BigText.() -> Unit): NotifyCreator {
        this.content = Payload.Content.BigText()
        (this.content as Payload.Content.BigText).init()
        return this
    }

    fun asBigPicture(init: Payload.Content.BigPicture.() -> Unit): NotifyCreator {
        this.content = Payload.Content.BigPicture()
        (this.content as Payload.Content.BigPicture).init()
        return this
    }

    fun stackable(init: Payload.Stackable.() -> Unit): NotifyCreator {
        this.stackable = Payload.Stackable()
        (this.stackable as Payload.Stackable).init()

        this.stackable
            ?.takeIf { it.key.isNullOrEmpty() }
            ?.apply {
                throw IllegalArgumentException("Invalid stack key provided.")
            }
        return this
    }


    fun actions(init: ArrayList<Action>.() -> Unit): NotifyCreator {
        this.actions = ArrayList()
        (this.actions as ArrayList<Action>).init()
        return this
    }

    fun asMessage(init: Payload.Content.Message.() -> Unit): NotifyCreator {
        this.content = Payload.Content.Message()
        (this.content as Payload.Content.Message).init()
        return this
    }


    fun asBuilder(): NotificationCompat.Builder {
        return notifyManager.asBuilder(
            RawNotification(
                alerts,
                content,
                header,
                meta,
                stackable,
                actions
            )
        )
    }

    fun show(id: Int? = null): Int {
        return notifyManager.show(id, asBuilder())
    }

    fun cancel(id: Int) {
        return notifyManager.cancelNotification(id)
    }
}