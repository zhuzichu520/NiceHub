package com.zhuzichu.android.shared.databinding.webview

import android.content.Context
import android.content.res.Configuration
import android.webkit.WebView
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["webReadme"], requireAll = false)
fun bindWebView(webView: WebView, webData: String?) {
    AppCompatDelegate.getDefaultNightMode()
    val css = if (isDark(webView.context)) "github_dark.css" else "github.css"
    val html = """
            <!DOCTYPE html>
            <html>
            <head>
            <style></style>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" type="text/css" href="file:///android_asset/md/${css}">
            </head>
            <body>
            $webData
            </body>
            </html>"""

    webData?.let {
        loadData(webView, html)
    }
}


private fun isDark(context: Context): Boolean {
    val mode = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
    return mode == Configuration.UI_MODE_NIGHT_YES
}

private fun loadData(webView: WebView, content: String?) {


    webView.loadDataWithBaseURL(
        "file:///android_asset/md/",
        content,
        "text/html",
        "UTF-8",
        null
    )//这种写法可以正确解码
}