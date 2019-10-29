package com.zhuzichu.android.widget.notify

data class NotifyConfig(
    internal var defaultHeader: Payload.Header = Payload.Header(),
    internal var defaultAlerting: Payload.Alerts = Payload.Alerts()
) {
    fun header(init: Payload.Header.() -> Unit): NotifyConfig {
        defaultHeader.init()
        return this
    }

    fun alerting(key: String, init: Payload.Alerts.() -> Unit): NotifyConfig {
        defaultAlerting = defaultAlerting.copy(channelKey = key)
        defaultAlerting.init()
        return this
    }
}
