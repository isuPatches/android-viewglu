/*
 * Copyright 2021 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.viewglu.sample

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.isupatches.android.viewglu.paste
import com.isupatches.android.viewglu.sample.base.BaseFragmentWithLayout
import com.isupatches.android.viewglue.sample.R
import com.isupatches.android.viewglue.sample.databinding.FragmentMainBinding

internal class MainFragment : BaseFragmentWithLayout(R.layout.fragment_main) {

    override val binding: FragmentMainBinding by paste(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = MainFragmentViewPagerAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.bound)
                1 -> getString(R.string.inflated)
                else -> error("Unhandled position $position for TabLayoutMediator")
            }
        }.attach()
    }
}

private class MainFragmentViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentBound.createInstance()
            1 -> FragmentInflated.createInstance()
            else -> error("Unhandled position $position for MainFragmentViewPagerAdapter")
        }
    }
}
