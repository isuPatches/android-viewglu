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

import androidx.navigation.testing.TestNavHostController
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.isupatches.android.viewglu.sample.testobjects.BaseRobot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class MainActivityTest {

    private lateinit var testNavHostController: TestNavHostController

    @Before
    @UiThreadTest
    fun setUp() {
        testNavHostController = TestNavHostController(
            InstrumentationRegistry.getInstrumentation().targetContext
        ).apply {
            setGraph(R.navigation.main_nav_graph)
            setCurrentDestination(R.id.mainFragment)
        }
    }

    @Test
    fun loadsMainFragment() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario
            .mainScreen {
                verifyCurrentScreen(R.id.mainFragment, testNavHostController)
            }
    }
}

private fun ActivityScenario<MainActivity>.mainScreen(
    block: MainActivityRobot.() -> Unit
) = MainActivityRobot().apply { block() }

private class MainActivityRobot : BaseRobot()
