package com.example.brastlewarkmobiletest.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.brastlewarkmobiletest.R
import com.example.brastlewarkmobiletest.adapters.InhabitantsAdapter
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @get: Rule
    var homeRule: ActivityScenarioRule<HomeActivity> = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun searchViewTestClick() {
        Espresso.onView(withId(R.id.searchView)).perform(click())

    }

    /*@Test
    fun searchViewTestTypeText() {
        Espresso.onView(withId(R.id.searchView)).perform(typeText("F"), closeSoftKeyboard())

        Espresso.onView(withId(R.id.searchView)).check(matches(withText("I")))
    }*/

    @Test
    fun recyclerViewClick() {
        Espresso.onView(withId(R.id.inhabitantsRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<InhabitantsAdapter.ViewHolder>(1, click()))
    }

}