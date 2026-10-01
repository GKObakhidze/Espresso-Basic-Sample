package com.example.android.testing.espresso.BasicSample

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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

    private val favoriteFood = "Pizza"
    private val firstMovie = "Interstellar"
    private val secondMovie = "Inception"

    @Test
    fun changeText_displaysFavoriteFoodInSameActivity() {
        onView(withId(R.id.editTextUserInput))
            .perform(
                typeText(favoriteFood),
                closeSoftKeyboard()
            )

        onView(withId(R.id.changeTextBt))
            .perform(click())

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(favoriteFood)))
    }

    @Test
    fun openActivity_displaysSecondMovieOnNextScreen() {
        onView(withId(R.id.editTextUserInput))
            .perform(
                typeText(firstMovie),
                closeSoftKeyboard()
            )

        onView(withId(R.id.changeTextBt))
            .perform(click())

        onView(withId(R.id.textToBeChanged))
            .check(matches(withText(firstMovie)))

        onView(withId(R.id.editTextUserInput))
            .perform(
                clearText(),
                typeText(secondMovie),
                closeSoftKeyboard()
            )

        onView(withId(R.id.activityChangeTextBtn))
            .perform(click())

        onView(withId(R.id.show_text_view))
            .check(matches(withText(secondMovie)))
    }
}