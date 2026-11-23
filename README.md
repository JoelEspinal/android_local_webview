# Android Local WebView

A small native Android demo in Kotlin that shows how to display web content inside an app
with `WebView`: an HTML page bundled with the app, or a remote website.

| Bundled page (default) | Website |
|---|---|
| <img src="https://github.com/user-attachments/assets/db0577e6-0df8-4870-8160-893b5f5bf0ee" height="450"/> | <img src="https://github.com/user-attachments/assets/02a916eb-8ae6-4a43-9f46-c3e5b984f549" height="450"/> |

## ✨ What it shows
- Serving files from `app/src/main/assets/` with AndroidX `WebViewAssetLoader`, over the secure
  `https://appassets.androidplatform.net` origin instead of `file://`
- Switching to a remote website by changing a single constant
- A minimal, safe WebView setup: JavaScript on, no file-URL access, no storage permissions
  (only `INTERNET`)

## 🛠️ Tech
Kotlin · Android SDK (min 24, target 34) · AndroidX WebKit · Gradle Kotlin DSL

## ▶️ Run it
Open the project in Android Studio and run the `app` configuration.

- **Your own HTML:** replace `app/src/main/assets/index.html` (add CSS, JS and images next to it).
- **A website:** in `MainActivity.kt`, set `START_URL` to the site's address:

```kotlin
const val START_URL = "https://www.google.com"
```
