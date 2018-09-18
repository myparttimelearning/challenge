package com.rumodigi.fanduelmobilechallenge.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.rumodigi.fanduelmobilechallenge.presentation.view.activity.PlayerComparisonActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {
    @Inject
    public Navigator(){}

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    public void navigateToPlayerComparison(Context context) {
        if (context != null) {
            Intent intentToLaunch = PlayerComparisonActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
