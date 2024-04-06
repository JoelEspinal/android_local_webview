package com.joelespinal.local_webview

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewAssetLoader.InternalStoragePathHandler
import androidx.webkit.WebViewClientCompat
import java.io.File


val PERMISSION_REQUEST_CODE = 1
class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webView)
        webView.getSettings().setAllowFileAccess(true);

        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkPermission())
            {
                // Code for above or equal 23 API Oriented Device
                // Your Permission granted already .Do next code
                loadContent()
            } else {
                // Code for permission
                requestPermission()
            }
        }
        else
        {
            // Code for Below 23 API Oriented Device
            // Do next code
            requestPermission()

        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.v("Permission", "Permission: " + permissions[0] + "was " + grantResults[0])
            //resume tasks needing this permission
            loadContent()
        }
    }

    fun loadContent() {
        val  assetLoader = WebViewAssetLoader.Builder()
            .build();

        webView.webViewClient = object : WebViewClientCompat() {

            @RequiresApi(21)
            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url)
            }

            @Suppress("deprecation")
            override // for API < 21
            fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(Uri.parse(url))
            }
        }

        var webViewSettings = webView.getSettings()
        webViewSettings.javaScriptEnabled = true
        webViewSettings.allowFileAccess = true
        webViewSettings.allowFileAccessFromFileURLs = true
        webViewSettings.allowContentAccess = true

        /*
            Pass Uri (file:///android_asset/<html-entry-point>)
            this Uri give access to "app/src/main/assets" where your html code is stored
            create the assets folder if is not present. placed in "app/src/main/assets"
         */
        webView.loadUrl(Uri.parse("file:///android_asset/index.html").toString())
        /*
            you can request to an external web site as follow
         */
//        webView.loadUrl("google.com")
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            this@MainActivity,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return if (result == PackageManager.PERMISSION_GRANTED) {
            true
        } else {
            false
        }
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.READ_MEDIA_VIDEO
            )) {
            Toast.makeText(
                this@MainActivity,
                "Read Media Video permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.READ_MEDIA_VIDEO),
                    PERMISSION_REQUEST_CODE
                )
            }
        }

        if (
            (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    )) {
            Toast.makeText(
                this@MainActivity,
                "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {

        }

        if ((ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                    )) {
            Toast.makeText(
                this@MainActivity,
                "Read External Storage permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {

        }

        if ((ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.READ_MEDIA_IMAGES)
                    )) {
            Toast.makeText(
                this@MainActivity,
                "Read Media Image permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {

        }

        if ( (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.READ_MEDIA_AUDIO)
                    )) {
            Toast.makeText(
                this@MainActivity,
                "Read Media Audio Storage permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {

        }

        if ( (ActivityCompat.shouldShowRequestPermissionRationale(
                this@MainActivity,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE)
                    )) {
            Toast.makeText(
                this@MainActivity,
                "Manage External Storage permission allows us to do store images. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {

        }
    }
}