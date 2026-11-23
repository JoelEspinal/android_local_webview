package com.joelespinal.local_webview

import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

/**
 * Shows web content inside a native Android screen.
 *
 * Local pages are served from `app/src/main/assets/` through [WebViewAssetLoader],
 * so they load over a secure `https://appassets.androidplatform.net` origin
 * instead of `file://`. To show a website instead, set [START_URL] to its address.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClientCompat() {
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? = assetLoader.shouldInterceptRequest(request.url)
        }

        webView.loadUrl(START_URL)
    }

    companion object {
        /** Bundled page. Replace with e.g. "https://www.google.com" to load a website. */
        const val START_URL = "https://appassets.androidplatform.net/assets/index.html"
    }
}
