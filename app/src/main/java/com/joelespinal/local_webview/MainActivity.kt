package com.joelespinal.local_webview

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    val PERMISSION_REQUEST_CODE = 1
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
                requestPermission() // Code for permission
            }
        }
        else
        {
            requestPermission()
            // Code for Below 23 API Oriented Device
            // Do next code
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
        var documentsFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {

                view?.settings?.javaScriptEnabled = true
                view?.settings?.allowFileAccess = true
                view?.settings?.allowFileAccessFromFileURLs = true
                view?.settings?.allowContentAccess = true
                view?.loadUrl(url!!)
                return true
            }
        }


        // webView.loadUrl("https://www.google.com/")

        webView.loadUrl("file:///$documentsFolder/index.html")
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