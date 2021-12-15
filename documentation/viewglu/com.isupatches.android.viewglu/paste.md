//[viewglu](../../index.md)/[com.isupatches.android.viewglu](index.md)/[paste](paste.md)

# paste

[androidJvm]\
inline fun <[VIEW_BINDING](paste.md) : ViewBinding> [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html).[paste](paste.md)(crossinline bindingInflater: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -> [VIEW_BINDING](paste.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)<[VIEW_BINDING](paste.md)>

####  Description

Helps to apply a ViewBinding to an [AppCompatActivity](https://developer.android.com/reference/kotlin/androidx/appcompat/app/AppCompatActivity.html) by invoking the inflater.

####  Notes

<ul><li>Uses the activity's layout inflater</li><li>The [LazyThreadSafetyMode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy-thread-safety-mode/index.html) is set to NONE</li><li>Still requires the activity to apply the inflated binding</li></ul>

####  Example Usage

<pre><code>
    private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
</code></pre>

#### Return

Lazy<ViewBinding>

#### Author

Patches Klinefelter

#### Since

07/2021

[androidJvm]\
inline fun <[VIEW_BINDING](paste.md) : ViewBinding> [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html).[paste](paste.md)(crossinline bindingInflater: ([LayoutInflater](https://developer.android.com/reference/kotlin/android/view/LayoutInflater.html)) -> [VIEW_BINDING](paste.md)): [Lazy](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy/index.html)<[VIEW_BINDING](paste.md)>

####  Description

Helps to apply a ViewBinding to an [Activity](https://developer.android.com/reference/kotlin/android/app/Activity.html) by invoking the inflater.

####  Notes

<ul><li>Uses the activity's layout inflater</li><li>The [LazyThreadSafetyMode](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-lazy-thread-safety-mode/index.html) is set to NONE</li><li>Still requires the activity to apply the inflated binding</li></ul>

####  Example Usage

<pre><code>
    private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
</code></pre>

#### Return

Lazy<ViewBinding>

#### Author

Patches Klinefelter

#### Since

08/2021

[androidJvm]\
fun <[VIEW_BINDING](paste.md)> [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[paste](paste.md)(): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)<[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), [VIEW_BINDING](paste.md)>

####  Description

Helps manage the ViewBinding for a [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html) so that it is properly destroyed and not leaked.

####  Notes

<ul><li>Attaches a lifecycle observer to the receiving fragment</li><li>Requires the fragment to set the binding value</li><li>Removes the binding when the Fragment's onDestroy is triggered</li><li>Throws and error when binding is accessed before onCreateView or after onDestroy</li></ul>

####  Example Usage

<pre><code>
    private var binding: FragmentMainBinding by paste()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
</code></pre>

#### Return

ReadWriteProperty<Fragment, ViewBinding>

#### Author

Patches Klinefelter

#### Since

07/2021

#### Throws

| | |
|---|---|
| [error](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/index.html) | When binding is accessed before onCreateView or after onDestroy |

[androidJvm]\
fun <[VIEW_BINDING](paste.md) : ViewBinding> [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[paste](paste.md)(viewBinder: ([View](https://developer.android.com/reference/kotlin/android/view/View.html)) -> [VIEW_BINDING](paste.md)): [ReadOnlyProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-only-property/index.html)<[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), [VIEW_BINDING](paste.md)>

####  Description

Helps manage the ViewBinding for a [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html) so that it is properly destroyed and not leaked.

####  Notes

<ul><li>Attaches a lifecycle observer to the receiving fragment</li><li>Requires the fragment to pass in the view to be be bound</li><li>Removes the binding when the Fragment's onDestroy is triggered</li><li>Access binding if at least initialized, otherwise attempts to create binding</li><li>Releases binding onDestroy by posting to main thread</li></ul>

####  Example Usage

<pre><code>
    private var binding: FragmentMainBinding by paste()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }
</code></pre>

#### Return

ReadOnlyProperty<Fragment, ViewBinding>

#### Author

Patches Klinefelter

#### Since

12/2021
