package com.zhuzichu.android.widget.notify

data class RawNotification(
    internal val alerting: Payload.Alerts,
    internal val content: Payload.Content,
    internal val header: Payload.Header,
    internal val meta: Payload.Meta,
    internal val stackable: Payload.Stackable?,
    internal val actions: ArrayList<Action>?
)
