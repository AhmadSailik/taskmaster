package com.example.firstandroidapplication;

//import android.support.test.espresso.contrib.RecyclerViewActions;
import android.view.View;

//import androidx.test.espresso.Espresso;
//import androidx.test.espresso.ViewAction;
//import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

//import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.*;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest  {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);
    public static final  String mName="Funny";
    public static final  String mTitile="Tlitle2";
    public static final  String mBody="Body";
    public static final  String mState="State";
    public static final  String mUserName="Ahmad";



    @Test
    public void newTask(){
        onView(withId(R.id.newTask)).perform(click());
        onView(withId(R.id.editTextTitile)).perform(typeText(mTitile));
        onView(withId(R.id.editTextBody)).perform(typeText(mBody));
        onView(withId(R.id.editTextState)).perform(typeText(mState));
        closeSoftKeyboard();
        onView(withId(R.id.submit)).perform(click());
        onView(withId(R.id.button2)).perform(click());
//        onView(withId(R.id.listOfButton)).perform(actionOnItemAtPosition(1 ,click()));
//        onView(ViewMatchers.withId(R.id.listOfButton)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));

//        onView(withText(mTitile)).check(matches(isDisplayed())).perform(click());
    }
    @Test
    public void setting(){
        onView(withId(R.id.sitting)).perform(click());
        onView(withId(R.id.editUserName)).perform(typeText(mUserName));
        closeSoftKeyboard();
        onView(withId(R.id.save)).perform(click());
        onView(withId(R.id.users)).check(matches(withText("welcome "+mUserName)));
    }
}