# AutoGirlBlur

Android Studio Kotlin prototype for a privacy-first visual content shield.

## What this version does
- Private, on-device protection toggle from the app.
- Opens Android Accessibility settings and overlay permission settings.
- Runs a foreground service and can cover the screen with a fail-safe black shield when protection is enabled.
- Does not upload screenshots, videos, or personal data.

## Important Android limitation
Android does not provide a universal, reliable API to inspect and blur pixels inside every third-party app. Accessibility services can be disabled by the user or restricted by Android/OEM policies. A future production version should add an explicit MediaProjection consent flow and a tested on-device vision model. This prototype intentionally avoids pretending that gender can be inferred accurately; a human/face-content policy is safer and more reliable than classifying people by gender.

## Open in Android Studio
1. Clone this private repository.
2. Open the folder in Android Studio Hedgehog or newer.
3. Let Gradle sync.
4. Run on Android 8.0+.
5. In the app, enable Accessibility Service and overlay permission, then switch Protection ON.

### AndroidIDE
This project is configured for AndroidIDE's Gradle 7.4.2 runtime: Android Gradle Plugin 7.3.1, Kotlin 1.7.20, and compile/target SDK 33.
The repository also includes `gradle/wrapper/gradle-wrapper.properties` pinned to Gradle 7.4.2 so AndroidIDE can recognize the project wrapper.

## Privacy and consent
This tool is for the device owner. Keep the service visible, show a persistent notification, and do not remove Android's permission/consent screens in production.
