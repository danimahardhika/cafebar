# CafeBar
[![](https://jitpack.io/v/danimahardhika/cafebar.svg)](https://jitpack.io/#danimahardhika/cafebar) [![Build Status](https://travis-ci.org/danimahardhika/cafebar.svg?branch=master)](https://travis-ci.org/danimahardhika/cafebar) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

An upgraded Snackbar for Android that provides more options and easy to use. Download sample apk from [here](https://github.com/danimahardhika/cafebar/releases/download/1.0.0/sample-release.apk).

<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/screenshot.jpg" height="456">
<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-0.gif" height="456">
<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-1.gif" height="456">

# Gradle Dependency
The minimum API level supported by this library is API 13

Add JitPack repository to root ```build.gradle```
```Gradle
allprojects {
    repositories {
        //...
        maven { url "https://jitpack.io" }
    }
}
```
Add the dependency
```Gradle
dependencies {
    //...
    compile 'com.github.danimahardhika:cafebar:1.0.1'
}
```

# Usage
Show simple CafeBar
```java
CafeBar.make(context, R.string.text, CafeBarDuration.SHORT).show();
```

Using builder
```java
CafeBar.builder(context)
    .theme(CafeBarTheme.LIGHT)
    .content(R.string.text)
    .neutralText("Action")
    .build().show();
```

Retrieve CafeBar view
```java
CafeBar.Builder builder = new CafeBar.Builder(context);
...

CafeBar cafeBar = builder.build();

View v = cafeBar.getCafeBarView();
//Do something

cafeBar.show();
```

Floating CafeBar
```java
 CafeBar.builder(context)
    .content("some text")
    .floating(true)
    .neutralText("Floating")
    .neutralColor(Color.parseColor("#EEFF41"))
    .build().show();
```

CafeBar above Translucent NavigationBar
```java
CafeBar.builder(context)
    .content(R.string.text)
    .fitSystemWindow(true)
    .neutralText("Above NavBar")
    .neutralColor(Color.parseColor("#EEFF41"))
    .build().show();
```

# Customization
Builder
- `customView()` &#8594; Use custom view
- `to()` &#8594; Set target view, it should be coordinator layout
- `content()` &#8594; Content text
- `contentTypeface()` &#8594; Custom typeface for content
- `maxLines()` &#8594; Max content lines, must be between 1 to 6
- `duration()` &#8594; Show duration
- `theme()` &#8594; CafeBar theme, there are 3 choices available `DARK` (default), `LIGHT`, and `CLEAR_BLACK`
- `icon()` &#8594; Icon shown on left side of content
- `showShadow()` &#8594; Enable or disable shadow
- `autoDismiss()` &#8594; Enable or disable auto dismiss, default is true
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
- `build()` &#8594; Create CafeBar

CafeBar
- `make()` &#8594; Create CafeBar
- `setAction()` &#8594; It's the same with neutral action from builder, if `neutralText()` already set from builder `setAction()` will be ignored
- `getCafeBarVIew()` &#8594; Get root view of CafeBar, default is `LinearLayout`
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
