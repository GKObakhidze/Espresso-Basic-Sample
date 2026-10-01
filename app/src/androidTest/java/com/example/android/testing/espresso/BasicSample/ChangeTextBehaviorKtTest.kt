package com.example.android.testing.espresso.BasicSample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    private val FAVORITE_FOOD = "Khachapuri"
    private val FIRST_FAVORITE_MOVIE = "Lion"
    private val SECOND_FAVORITE_MOVIE = "Before Sunset"

    @Test
    fun changeTextDisplaysFavoriteFoodInSameActivity() {

        onView(withId(R.id.editTextUserInput))
            .perform(typeText(FAVORITE_FOOD), closeSoftKeyboard())

        onView(withId(R.id.changeTextBt))
            .perform(click())

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(FAVORITE_FOOD)))
    }

    @Test
    fun changeTextDisplaysDifferentMovieInNewActivity() {

        onView(withId(R.id.editTextUserInput))
            .perform(
                typeText(FIRST_FAVORITE_MOVIE),
                closeSoftKeyboard()
            )

        onView(withId(R.id.changeTextBt))
            .perform(click())

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(FIRST_FAVORITE_MOVIE)))

        onView(withId(R.id.editTextUserInput))
            .perform(
                clearText(),
                typeText(SECOND_FAVORITE_MOVIE),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activityChangeTextBtn))
            .perform(click())

        onView(withId(R.id.show_text_view))
            .check(matches(withText(SECOND_FAVORITE_MOVIE)))
    }
}