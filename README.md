# Android ViewGlu

A tool to limit the amount of boilerplate with Android's View Binding.

> <br/>*Developed by Patches 07/2021 - present* <br/>
> <br/>Supports Android SDK levels 18-30<br/><br/>
> <br/>Compiled with Java11<br/><br/>

- [Adding to your project](#adding-to-your-project)
- [Prerequisite](#prerequisite)
- [Usage](#usage)
    - [For An Activity](#for-an-activity)
    - [For A Fragment](#for-a-fragment)
- [What's New](#whats-new)
- [Dependencies Used](#dependencies-used)
- [Additional Resources](#additional-resources)
- [License](#license)

## Adding to your project

Make sure you have one of the following repository accessible:

```groovy
    repositories {
        mavenCentral()
    }
```

and then add it as a dependency. Please see the [release page](https://github.com/isupatches/android-viewglu/releases) for the latest version.

For Gradle:

```groovy
    implementation 'com.isupatches.android:viewglu:<LATEST_VERSION>'
```

For Maven:

```xml
    <dependency>
      <groupId>com.isupatches.android</groupId>
      <artifactId>viewglu</artifactId>
      <version>LATEST_VERSION</version>
      <type>pom</type>
    </dependency>
```

You may also download the @aar from the [release page](https://github.com/isupatches/android-viewglu/releases) and import it into your project manually. 

## Prerequisite

There is only one prerequisite for this library and that is that `viewBinding` must be enabled: 

```kotlin
buildFeatures {
    viewBinding = true
}
```

## Usage

### For An Activity

The `Binding` class for an activity can be attached in a simple one-liner that leverages the layout inflater from the activity:

```kotlin
internal class MainActivity : BaseActivity() {

    override val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)
}
```

The activity is then responsible for setting the content view with the bindings root:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)
}
```

See the [MainActivity](/app/src/main/java/com/isupatches/android/viewglu/sample/MainActivity.kt) for a full example.

### For A Fragment

Fragments are slightly different so that memory constraints and leaks are prevented. Similar to activities though, they have to signal that the binding's root should be used:

```Kotlin
private var binding: FragmentMainBinding by paste()

override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
}
```

---
> ViewGlu will automagically detach the binding during the Fragment's `onDestroy`
---

See the [MainFragment](/app/src/main/java/com/isupatches/android/viewglu/sample/MainFragment.kt) for a full example.

## What's New?

Please checkout the [CHANGELOG](CHANGELOG.md) and the [release notes](https://github.com/isuPatches/android-viewglu/releases) for details on what's been updated and improved.

## Dependencies Used

To provide the libraries functionality:

- [androidx.appcompat:appcompat](https://developer.android.com/jetpack/androidx/releases/appcompat)
- [androidx.lifecycle:lifecycle-runtime](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [androidx.lifecycle:lifecycle-compiler](https://developer.android.com/jetpack/androidx/releases/lifecycle)
- [org.jetbrains.kotlin:kotlin-stdlib](https://kotlinlang.org/api/latest/jvm/stdlib/)

For static analysis:

- [KTLint](https://github.com/pinterest/ktlint)
- [Detekt](https://github.com/detekt/detekt)
- [PMD](https://github.com/pmd/pmd)
- [CPD](https://github.com/aaschmid/gradle-cpd-plugin)
- [Dexcount](https://github.com/KeepSafe/dexcount-gradle-plugin)

For code coverage:

- [Jacoco](https://github.com/jacoco/jacoco)

For publishing:

- [maven-publish](https://docs.gradle.org/current/userguide/publishing_maven.html)

For documentation

- [Dokka](https://github.com/Kotlin/dokka)

## Additional Resources

- [Use view binding in activities](https://developer.android.com/topic/libraries/view-binding#activities)
- [Use view binding in fragments](https://developer.android.com/topic/libraries/view-binding#fragments)

## License

Copyright 2021 Patches Klinefelter

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
compliance with the License. You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License
is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
or implied. See the License for the specific language governing permissions and limitations under
the License.
