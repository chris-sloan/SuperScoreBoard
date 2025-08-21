# 🔧 Widget TroubleshootingCheat-Sheet

- Verify the provider XML is packaged
   `adb shell ls -R "$(adb shell pm path <app.id> | cut -d: -f2)"/res/xml | grep countdown_widget_info.xml`
   Expected: path to countdown_widget_info.xml.
   Missing? The file isn’t in the APK — check module dependency or file path. 

- Look for provider‑XML parse errors
   `adb logcat | grep -i AppWidgetProviderInfo`
   Any “Error inflating …” line points to an invalid or missing attribute (often `android:initialLayout` or `android:previewLayout`). 

- Confirm Android registered the widget provider
   `adb shell dumpsys appwidget | grep -A3 CountdownReceiver`
   Healthy output (non‑zero sizes & real resource IDs) should resemble:
   provider …CountdownReceiver}
   min=(120x120dp)   minResize=(120x120dp)
   widgetCategory=home_screen|keyguard
   initialLayout=#0x7f0b0062
   All zeros → XML didn’t load (return to step 2). 

- Force the launcher to refresh its cache
   `adb shell am force-stop com.android.launcher3`   # replace pkg if using a different launcher
   —or just reboot the device.

- Re‑install after any XML or manifest edits
   `adb install -r app-debug.apk`    # full reinstall beats Apply‑Changes
   Minimal res/xml/countdown_widget_info.xml
   ```xml
   <appwidget-provider xmlns:android="http://schemas.android.com/apk/res/android"
       android:minWidth="120dp"
       android:minHeight="120dp"
       android:updatePeriodMillis="0"
       android:initialLayout="@layout/widget_loading_stub"
       android:previewLayout="@layout/widget_countdown_preview"
       android:widgetCategory="home_screen|keyguard"/>
   ```
