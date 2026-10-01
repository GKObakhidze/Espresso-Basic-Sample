/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * The kotlin equivalent to the ChangeTextBehaviorTest, that
 * showcases simple view matchers and actions like [ViewMatchers.withId],
 * [ViewActions.click] and [ViewActions.typeText], and ActivityScenarioRule
 *
 *
 * Note that there is no need to tell Espresso that a view is in a different [Activity].
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun changeText_displaysFavoriteFoodInSameActivity() {
        // Enter the favorite food and close the keyboard
        onView(withId(R.id.editTextUserInput))
            .perform(typeText(Constants.FAVORITE_FOOD), closeSoftKeyboard())

        // Tap the "Change text" button
        onView(withId(R.id.changeTextBt)).perform(click())

        // Verify the food name is shown in the text above the input field
        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(Constants.FAVORITE_FOOD)))
    }

    @Test
    fun changeTextAndOpenActivity_displaysSecondMovieOnNewScreen() {
        // Enter the first movie and close the keyboard
        onView(withId(R.id.editTextUserInput))
            .perform(typeText(Constants.FIRST_MOVIE), closeSoftKeyboard())

        // Tap the "Change text" button
        onView(withId(R.id.changeTextBt)).perform(click())

        // Verify the first movie is shown in the text above the input field
        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(Constants.FIRST_MOVIE)))

        // Clear the field, enter the second movie and close the keyboard
        onView(withId(R.id.editTextUserInput))
            .perform(clearText(), typeText(Constants.SECOND_MOVIE), closeSoftKeyboard())

        // Tap the "Open activity and change text" button
        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        // Verify the second movie is shown on the next screen
        onView(withId(R.id.show_text_view))
            .check(matches(withText(Constants.SECOND_MOVIE)))
    }
}