# CafeBar
[![](https://jitpack.io/v/danimahardhika/cafebar.svg)](https://jitpack.io/#danimahardhika/cafebar) [![Build Status](https://travis-ci.org/danimahardhika/cafebar.svg?branch=master)](https://travis-ci.org/danimahardhika/cafebar) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/0f4b6a7685d54df59ad87fd3239fd9ed)](https://www.codacy.com/app/danimahardhika/cafebar?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=danimahardhika/cafebar&amp;utm_campaign=Badge_Grade) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

An upgraded Snackbar for Android that provides more options and easy to use. Download sample apk from [here](https://github.com/danimahardhika/cafebar/releases/download/1.1.0/app-release.apk).

<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/screenshot.jpg" height="456"> <img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-0.gif" height="456"> <img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-1.gif" height="456">

# Gradle Dependency
The minimum API level supported by this library is API 14

Add JitPack repository to root ```build.gradle```
```Gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency
```Gradle
dependencies {
    implementation 'com.github.danimahardhika:cafebar:1.3.1'
}
```

# Usage
Old release [1.0.0](https://github.com/danimahardhika/cafebar/releases/tag/1.0.0) - [1.2.0](https://github.com/danimahardhika/cafebar/releases/tag/1.2.0) take a look [here](https://github.com/danimahardhika/cafebar/wiki/Old-Readme)

#### Show simple CafeBar
```java
CafeBar.make(context, R.string.text, CafeBar.Duration.SHORT).show();
```

#### Show simple CafeBar with action
```java
CafeBar.make(context, R.string.text, CafeBar.Duration.SHORT)
    .setAction(actionText, actionColor, callback)
    .show();
```

#### Using builder
```java
CafeBar.builder(context)
    .theme(CafeBarTheme.LIGHT)
    .duration(CafeBar.Duration.MEDIUM)
    .content(R.string.text)
    .neutralText("Action")
    //You can parse string color
    .neutralColor(Color.parseColor("#EEFF41"))
    //Or use color resource
    .neutralColor(R.color.neutralText)
    .show();
```

#### Retrieve CafeBar view
```java
CafeBar.Builder builder = new CafeBar.Builder(context);
...
CafeBar cafeBar = builder.build();

View v = cafeBar.getView();
//Do something
cafeBar.show();
```

#### Floating CafeBar
```java
 CafeBar.builder(context)
    .content("some text")
    .floating(true)
    .show();
```

#### CafeBar above Translucent NavigationBar
```java
CafeBar.builder(context)
    .content(R.string.text)
    //automatically determine if device has soft navigation bar and translucent navigation bar
    .fitSystemWindow()
    .show();
```

#### Custom Theme
```java
CafeBar.builder(context)
    //Text color (content and buttons) automatically set
    .theme(CafeBarTheme.Custom(Color.parseColor("#F44336")));
    .content(R.string.text)
    .show();
```

#### Custom Theme
```java
CafeBar.builder(context)
    .content(R.string.text)
    .callback(new Snackbar.Callback() {
        @Override public void onDismissed(Snackbar transientBottomBar, int event) {
            super.onDismissed(transientBottomBar, event);
            Toast.makeText(context, "CafeBar dismissed", Toast.LENGTH_LONG).show();
        }

        @Override public void onShown(Snackbar sb) {
            super.onShown(sb);
            Toast.makeText(context, "CafeBar shown", Toast.LENGTH_LONG).show();
        }
    })
    .show();
```

#### Custom Font
```java
CafeBar.builder(context)
    .content(R.string.text)
    //You must place your font inside assets/fonts/ folder
    .contentTypeface("RobotoMono-Regular.ttf")
    //Or
    .contentTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/RobotoMono-Regular.ttf");
    .show();
```

# Customization
Builder
- `customView()` &#8594; Use custom view
- `to()` &#8594; Set target view, it should be `CoordinatorLayout`
- `content()` &#8594; Content text
- `contentTypeface()` &#8594; Custom typeface for content
- `maxLines()` &#8594; Max content lines, must be between 1 to 6.
- `duration()` &#8594; Show duration
- `theme()` &#8594; CafeBar theme, there are 3 choices available `DARK` (default), `LIGHT`, and `CLEAR_BLACK`. You can use custom theme `CafeBarTheme.Custom(int)`.
- `icon()` &#8594; Icon shown on left side of content
- `showShadow()` &#8594; Enable or disable shadow
- `autoDismiss()` &#8594; Enable or disable auto dismiss, default is true
- `swipeToDismiss()` &#8594; Enable or disable swipe to dismiss, only works with target view `CoordinatorLayout`.
- `floating()` &#8594; Set CafeBar style to floating
- `gravity()` &#8594; Set CafeBar view gravity, only works for tablet and floating
- `fitSystemWindow()` &#8594; Show CafeBar above translucent navigation bar
- `neutralText()` &#8594; Neutral action text
- `neutralColor()` &#8594; Neutral action text color
- `neutralTypeface()` &#8594; Custom typeface for neutral text
- `onNeutral()` &#8594; Neutral action callback
- `positiveText()` &#8594; Positive action text
- `positiveColor()` &#8594; Positive action text color
- `positiveTypeface()` &#8594; Custom typeface for positive text
- `onPositive()` &#8594; Positive action callback
- `negativeText()` &#8594; Negative action text
- `negativeColor()` &#8594; Negative action text color
- `negativeTypeface()` &#8594; Custom typeface for negative text
- `onNegative()` &#8594; Negative action callback
- `buttonColor()` &#8594; Set all buttons color
- `buttonTypeface()` &#8594; Set all buttons typeface
- `typeface()` &#8594; Set content and button typeface
- `show()` &#8594; Show CafeBar directly from builder
- `build()` &#8594; Create CafeBar

CafeBar
- `make()` &#8594; Create CafeBar
- `setAction()` &#8594; It's the same with neutral action from builder, if `neutralText()` already set from builder `setAction()` will be ignored.
- `getView()` &#8594; Get root view of CafeBar
- `show()` &#8594; Show CafeBar
- `dismiss()` &#8594; Dismiss CafeBar

# License
```
Copyright (c) 2017 Dani Mahardhika

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
