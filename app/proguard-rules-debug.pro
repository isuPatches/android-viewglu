-dontobfuscate

-include proguard-rules-common.pro

-keepattributes SourceFile, LineNumberTable

-keep class androidx.fragment.app.testing.** { *; }

-keep class androidx.test.core.app.ActivityScenario { *; }
-keep class androidx.test.internal.platform.** { *; }
-keep class androidx.test.internal.runner.** { *; }
-keep class androidx.test.platform.** { *; }
-keep class androidx.test.runner.** { *; }

-keep class androidx.navigation.NavHostController {
    createDeepLink();
    setNavigatorProvider(...);
}

-keep class androidx.navigation.NavDeepLinkBuilder {
    createTaskStackBuilder(...);
    setArguments(...);
    setDestination(...);
}

-keep class kotlin.jvm.internal.Intrinsics {
    checkParameterIsNotNull(...);
    checkExpressionValueIsNotNull(...);
}
