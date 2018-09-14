package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.rumodigi.fanduelmobilechallenge.presentation.AndroidApplication;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.ApplicationComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.ActivityModule;
import com.rumodigi.fanduelmobilechallenge.presentation.navigation.Navigator;

import javax.inject.Inject;

public abstract class BaseActivity extends Activity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}
