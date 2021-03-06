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

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.isupatches.android.viewglu.sample.testobjects.BaseRobot
import com.isupatches.android.viewglu.sample.testobjects.swipeNext
import com.isupatches.android.viewglu.sample.testobjects.swipePrevious
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainFragmentTest {

    @Test
    fun displaysBoundFragmentInViewPager() {
        val scenario = launchFragmentInContainer<MainFragment>(themeResId = R.style.AppTheme)
        scenario
            .mainScreen {
                swipeNextOnViewPager()
                swipePreviousOnViewPager()
                checkTextIsDisplayed(R.string.bound_fragment_text)
            }
    }

    @Test
    fun displaysInflatedFragmentInViewPager() {
        val scenario = launchFragmentInContainer<MainFragment>(themeResId = R.style.AppTheme)
        scenario
            .mainScreen {
                swipeNextOnViewPager()
                checkTextIsDisplayed(R.string.inflated_fragment_text)
            }
    }
}

private fun FragmentScenario<MainFragment>.mainScreen(
    block: MainFragmentRobot.() -> Unit
) = MainFragmentRobot().apply { block() }

private class MainFragmentRobot : BaseRobot() {

    fun swipePreviousOnViewPager() {
        onView(withId(R.id.viewPager)).perform(swipePrevious())
    }

    fun swipeNextOnViewPager() {
        onView(withId(R.id.viewPager)).perform(swipeNext())
    }
}
