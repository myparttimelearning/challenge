package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.rumodigi.fanduelmobilechallenge.presentation.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;


@RunWith(AndroidJUnit4.class)

public class MainActivityTest {

    /**
     *
     * Tests passed on physical device: Nexus 7 tablet - Android M with WiFi connection
     *
     * If running on emulator or phone with mobile data connection, further instrumentation steps
     * would be required, namely turn off/on mobile data.
     *
     */

    WifiManager wifiManager;
    IdlingResource connectivityIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new IntentsTestRule<>(MainActivity.class);

    @Before
    public void setUp(){
        wifiManager = (WifiManager) activityRule.getActivity().getSystemService(Context.WIFI_SERVICE);
    }

    @After
    public void tearDown(){
        Espresso.unregisterIdlingResources(connectivityIdlingResource);
    }

    @Test
    public void testClickStartGame_confirmCallToNavigator() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        connectivityIdlingResource = new ConnectivityIdlingResource("Wifi", appContext, ConnectivityIdlingResource.WAIT_FOR_CONNECTION);
        Espresso.registerIdlingResources(connectivityIdlingResource);

        wifiManager.setWifiEnabled(true);
        onView(withId(R.id.startGame))
                .perform(click());
        intended(hasComponent(PlayerComparisonActivity.class.getName()));
    }

    @Test
    public void testClickStartGame_confirmToast() {

        Context appContext = InstrumentationRegistry.getTargetContext();
        connectivityIdlingResource = new ConnectivityIdlingResource("Wifi", appContext, ConnectivityIdlingResource.WAIT_FOR_DISCONNECTION);
        Espresso.registerIdlingResources(connectivityIdlingResource);

        wifiManager.setWifiEnabled(false);
        onView(withId(R.id.startGame))
                .perform(click());
        onView(withText(R.string.no_internet)).inRoot(withDecorView(not(activityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
}