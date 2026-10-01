package com.example.android.testing.espresso.BasicSample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeTextBehaviorKtTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun verifyTextChangeInSameActivity() {
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("Xachapuri"), closeSoftKeyboard())

        onView(withId(R.id.changeTextBt)).perform(click())

        onView(withId(R.id.textToBeChanged)).check(matches(withText("Xachapuri")))
    }

    @Test
    fun verifyTextChangeInNewActivity() {
        onView(withId(R.id.editTextUserInput))
            .perform(typeText("National Treasure"), closeSoftKeyboard())

        onView(withId(R.id.changeTextBt)).perform(click())

        onView(withId(R.id.textToBeChanged)).check(matches(withText("National Treasure")))

        onView(withId(R.id.editTextUserInput))
            .perform(clearText(), typeText("Harry Potter"), closeSoftKeyboard())

        onView(withId(R.id.activityChangeTextBtn)).perform(click())

        onView(withId(R.id.show_text_view)).check(matches(withText("Harry Potter")))
    }
}