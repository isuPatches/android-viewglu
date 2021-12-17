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
package com.isupatches.android.viewglu.sample.testobjects

import android.view.View
import androidx.core.view.ViewCompat
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast
import androidx.viewpager2.widget.ViewPager2
import com.isupatches.android.viewglu.sample.testobjects.SwipeAction.Direction.BACKWARD
import com.isupatches.android.viewglu.sample.testobjects.SwipeAction.Direction.FORWARD
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.anyOf

/**
 * ViewAction that issues a swipe gesture on a [ViewPager2] to move that ViewPager2 to the next
 * page, taking orientation and layout direction into account.
 */
fun swipeNext(): ViewAction {
    return SwipeAction(FORWARD)
}

/**
 * ViewAction that issues a swipe gesture on a [ViewPager2] to move that ViewPager2 to the previous
 * page, taking orientation and layout direction into account.
 */
fun swipePrevious(): ViewAction {
    return SwipeAction(BACKWARD)
}

private class SwipeAction(val direction: Direction) : ViewAction {
    enum class Direction {
        FORWARD,
        BACKWARD
    }

    override fun getDescription(): String = "Swiping $direction"

    override fun getConstraints(): Matcher<View> =
        allOf(
            anyOf(
                isAssignableFrom(ViewPager2::class.java),
                isDescendantOfA(isAssignableFrom(ViewPager2::class.java))
            ),
            isDisplayingAtLeast(90)
        )

    override fun perform(uiController: UiController, view: View) {
        val vp = if (view is ViewPager2) {
            view
        } else {
            var parent = view.parent
            while (parent !is ViewPager2 && parent != null) {
                parent = parent.parent
            }
            parent as ViewPager2
        }
        val isForward = direction == FORWARD
        val swipeAction: ViewAction = if (vp.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            if (isForward == vp.isRtl()) swipeRight() else swipeLeft()
        } else {
            if (isForward) swipeUp() else swipeDown()
        }
        swipeAction.perform(uiController, view)
    }

    private fun ViewPager2.isRtl(): Boolean {
        return ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL
    }
}
