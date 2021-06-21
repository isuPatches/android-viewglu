package com.isupatches.android.viewglu.sample

import com.isupatches.android.viewglu.paste
import com.isupatches.android.viewglu.sample.base.BaseActivity
import com.isupatches.android.viewglue.sample.databinding.ActivityMainBinding

internal class MainActivity : BaseActivity() {

    override val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)
}
