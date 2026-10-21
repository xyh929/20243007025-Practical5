# PracticalFive - Chinese Municipalities

Java application logic and XML Views. Open this folder in Android Studio, wait for Gradle sync, and run the app configuration on an Android device (API 24 or later).

## Usage
Enter Beijing, Shanghai, Tianjin or Chongqing, then press the keyboard Go/Enter action. Case is ignored and surrounding spaces are removed. Empty and unsupported names display an input error. The next screen shows the selected city, photo, original summary and required colour scheme. Visit site sends an ACTION_VIEW intent for that city. Back returns to the input screen.

## Project structure
- MainActivity.java: keyboard submission, validation and explicit navigation.
- TextProcessor.java: shared input validation without Android dependencies.
- Municipality.java: safe Intent extra handling, dynamic resources and official website intent.
- res/layout: the two XML layouts.
- res/values/arrays.xml: Beijing, Shanghai, Chongqing, Tianjin (the written requirement order, correcting the inconsistent example).
- SOURCES.md: official research references and supplied image attribution.
- verification: simulator screenshots, runtime results and a standalone validation test.

## Verification
- assembleDebug: PASS; debug APK at app/build/outputs/apk/debug/app-debug.apk.
- TextProcessor: 11 cases PASS (null, empty, whitespace, invalid city, all supported cities, mixed case and padded input).
- Pixel_6 emulator: four detail screens PASS, including matching titles and summary prefixes.
- Visit site: each button launched an ACTION_VIEW request with the corresponding official hostname, verified in Android ActivityTaskManager logs. This checks the browser handoff; it does not assert remote page rendering or network availability.
- Runtime invalid inputs: empty, whitespace and Guangzhou remain on the input screen.
- Screenshots reviewed for Beijing and Chongqing; title, photo, information and fixed bottom button visible.
- lintDebug could not complete: intellij-core-32.3.3.jar and kotlin-compiler-32.3.3.jar are missing from the local lint cache. Offline lint reports dependency resolution failure; online lint stalled during tool retrieval. APK compilation and simulator validation passed independently.

## Submission still required
Create a new PUBLIC GitHub/GitLab repository named STUDENT_ID-Practical5 without initializing remote files. Add its URL as origin and push this repository, preserving the existing incremental commits. No remote has been configured or pushed because the student ID and destination repository have not been supplied. The assignment asks for incremental pushes during development; these local commits preserve actual stages, but no remote pushes have occurred. Submit the public repository HTTPS URL on Canvas.

Do not place this project in a directory containing Chinese characters: the installed Android Gradle plugin rejects such paths on Windows.
