# PracticalFive - Chinese Municipalities

Student: Xiaoyoheng
Student ID: 20243007025
Public repository: https://github.com/xyh929/20243007025-Practical5

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
- Rechecked on 2026-10-30: assembleDebug and lintDebug both completed successfully after downloading required dependencies. The standalone TextProcessor check again passed all 11 cases. A fresh Pixel_6 emulator run on 2026-10-30 passed all 10 runtime cases (seven supported/case variants and three invalid inputs). Four city screenshots and all three error popups were visually reviewed. Browser handoffs were checked against ActivityTaskManager logs. Fresh evidence is in verification/2026-10-30; the repeatable script is verification/runtime_check.py.

## Submission and version control
The public repository is named `20243007025-Practical5`. It was created blank, without a remote README, .gitignore or license. Actual local development commits were preserved without force-pushing or changing commit dates. On 2026-10-30, the remote main branch was absent; all 11 existing commits were uploaded together, and anonymous GitHub API access confirmed that the repository is public.

Timing disclosure: the initial Android project and app features were developed locally before the remote repository was created. Therefore the original requirement to link the remote immediately and push during development was not met at that time. The later upload preserves the real development history but does not retroactively satisfy that timing requirement. The project was prepared and pushed using Git CLI rather than Android Studio built-in Git controls.

Submit this HTTPS URL in the designated Practical 5 submission on Canvas:
https://github.com/xyh929/20243007025-Practical5

Canvas submission must still be performed and confirmed by the student.

Do not place this project in a directory containing Chinese characters: the installed Android Gradle plugin rejects such paths on Windows.
