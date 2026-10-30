# Requirements review — 2026-10-30

- Project PracticalFive; Java application code and XML layouts: inspected.
- App name, supplied city images, launcher icon references and named colours: inspected.
- municipalities_array order: Beijing, Shanghai, Chongqing, Tianjin. Summary word counts: 97, 99, 100, 100.
- Main screen: title, image, labelled single-line input and Go/Done/Enter handling: inspected.
- TextProcessor: shared non-Activity class; null, blank, unsupported and case-insensitive validation: 11 standalone checks passed.
- Explicit Intent: shared extra key; destination validates missing or unsupported input safely: inspected.
- Detail screen: corresponding title, photo, summary and colours; weighted ScrollView keeps Visit site accessible: inspected.
- Implicit ACTION_VIEW: selected official URL and missing-handler exception handling: inspected.
- Official homepages: direct HTTPS requests to english.beijing.gov.cn, english.shanghai.gov.cn, en.tj.gov.cn and english.cq.gov.cn all returned HTTP 200 on 2026-10-30.
- assembleDebug and lintDebug: successful on 2026-10-30. Lint success does not mean zero warnings.
- Runtime retest on Pixel_6, 2026-10-30: all 10 input cases passed. Four city screenshots and three validation error screenshots were visually checked. ACTION_VIEW logs matched all four official hosts. Evidence is stored in verification/2026-10-30. Browser page rendering was not asserted.
- GitHub: anonymous API confirmed public repository xyh929/20243007025-Practical5; actual development commits retained and pushed.

## Historical process requirement

The original requirement to connect the remote immediately after project creation and push each feature during development was not met. Later uploads and this review cannot retroactively satisfy it. No commit dates or development history were rewritten. The existing implementation did not require reconstruction based on this review.

## Submission

Submit https://github.com/xyh929/20243007025-Practical5 on Canvas. Canvas submission has not been performed or confirmed here.
