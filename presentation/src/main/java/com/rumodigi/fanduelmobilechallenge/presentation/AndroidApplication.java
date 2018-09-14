package com.rumodigi.fanduelmobilechallenge.presentation;

import android.app.Application;

import com.rumodigi.fanduelmobilechallenge.presentation.di.components.ApplicationComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.DaggerApplicationComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.ApplicationModule;
import com.squareup.leakcanary.LeakCanary;

public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        this.initializeLeakDetection();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    private void initializeLeakDetection() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
