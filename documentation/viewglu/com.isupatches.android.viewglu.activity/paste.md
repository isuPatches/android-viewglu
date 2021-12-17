//[viewglu](../../index.md)/[com.isupatches.android.viewglu.activity](index.md)/[paste](paste.md)

# paste

[androidJvm]\
inline fun <[VIEW_BINDING](paste.md) : ViewBinding> [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[paste](paste.md)(crossinline bindingInflater: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -> [VIEW_BINDING](paste.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)<[VIEW_BINDING](paste.md)>

####  Description

Helps to apply a ViewBinding to an [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html) by invoking the inflater.

####  Notes

<ul><li>Uses the activity's layout inflater</li><li>The [LazyThreadSafetyMode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy-thread-safety-mode/index.html) is set to NONE</li><li>Still requires the activity to apply the inflated binding</li></ul>

####  Example Usage

<pre><code>
internal class MainActivity : Activity() {
    private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}
</code></pre>

#### Return

Lazy<ViewBinding>

#### Author

Patches Klinefelter

#### Since

08/2021
