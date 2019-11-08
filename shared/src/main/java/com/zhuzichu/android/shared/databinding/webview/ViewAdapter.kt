package com.zhuzichu.android.shared.databinding.webview

import android.webkit.WebView
import androidx.databinding.BindingAdapter
import com.zhuzichu.android.shared.extension.isDark

@BindingAdapter(value = ["webReadme"], requireAll = false)
fun bindWebView(webView: WebView, webData: String?) {
    val css = if (webView.context.isDark()) "readme_dark.css" else "readme.css"
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

private fun loadData(webView: WebView, content: String?) {
    webView.loadDataWithBaseURL(
        "file:///android_asset/md/",
        content,
        "text/html",
        "UTF-8",
        null
    )//这种写法可以正确解码
}