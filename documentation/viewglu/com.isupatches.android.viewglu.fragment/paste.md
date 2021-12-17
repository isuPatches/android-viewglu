//[viewglu](../../index.md)/[com.isupatches.android.viewglu.fragment](index.md)/[paste](paste.md)

# paste

[androidJvm]\
fun <[VIEW_BINDING](paste.md)> [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html).[paste](paste.md)(): [ReadWriteProperty](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.properties/-read-write-property/index.html)<[Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html), [VIEW_BINDING](paste.md)>

####  Description

Helps manage the ViewBinding for a [Fragment](https://developer.android.com/reference/kotlin/androidx/fragment/app/Fragment.html) so that it is properly destroyed and not leaked.

####  Notes

<ul><li>Attaches a lifecycle observer to the receiving fragment</li><li>Requires the fragment to set the binding value</li><li>Removes the binding when the Fragment's onDestroy is triggered</li><li>Throws and error when binding is accessed before onCreateView or after onDestroy</li></ul>

####  Example Usage

<pre><code>
internal class FragmentInflated : BaseFragment() {
    private var binding: FragmentWithTextBinding by paste()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWithTextBinding.inflate(inflater, container, false)
        return binding.root
    }
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
internal class FragmentBound : BaseFragmentWithLayout(R.layout.fragment_with_text) {
    override val binding: FragmentWithTextBinding by paste(FragmentWithTextBinding::bind)
}
</code></pre>

#### Return

ReadOnlyProperty<Fragment, ViewBinding>

#### Author

Patches Klinefelter

#### Since

12/2021
