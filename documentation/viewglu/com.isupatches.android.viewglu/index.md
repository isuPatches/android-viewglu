//[viewglu](../../index.md)/[com.isupatches.android.viewglu](index.md)

# Package com.isupatches.android.viewglu

## Functions

| Name | Summary |
|---|---|
| [paste](paste.md) | [androidJvm]<br>fun <[VIEW_BINDING](paste.md)> [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[paste](paste.md)(): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)<[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), [VIEW_BINDING](paste.md)><br> Description<br>Helps manage the ViewBinding for a [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html) so that it is properly destroyed and not leaked.<br>[androidJvm]<br>inline fun <[VIEW_BINDING](paste.md) : ViewBinding> [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html).[paste](paste.md)(crossinline bindingInflater: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -> [VIEW_BINDING](paste.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)<[VIEW_BINDING](paste.md)><br> Description<br>Helps to apply a ViewBinding to an [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html) by invoking the inflater. |