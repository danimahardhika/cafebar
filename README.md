# CafeBar
[![](https://jitpack.io/v/danimahardhika/cafebar.svg)](https://jitpack.io/#danimahardhika/cafebar) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0) 

An upgraded Snackbar for Android that provides more options and easy to use. Download sample apk from [here](https://github.com/danimahardhika/cafebar/releases/download/1.0.0/sample-release.apk).

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
    compile 'com.github.danimahardhika:cafebar:1.0.0'
}
```

# Previews
<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/screenshot.jpg" height="440">
<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-0.gif" height="440">
<img src="https://raw.githubusercontent.com/danimahardhika/cafebar/master/arts/demo-1.gif" height="440">

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
