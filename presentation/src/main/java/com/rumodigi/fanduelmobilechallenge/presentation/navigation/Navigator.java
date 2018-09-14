package com.rumodigi.fanduelmobilechallenge.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.rumodigi.fanduelmobilechallenge.presentation.view.activity.PlayerListActivity;

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
    public void navigateToUserList(Context context) {
        if (context != null) {
            Intent intentToLaunch = PlayerListActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }
}
