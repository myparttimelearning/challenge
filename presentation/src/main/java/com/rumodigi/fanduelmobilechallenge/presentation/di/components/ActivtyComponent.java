package com.rumodigi.fanduelmobilechallenge.presentation.di.components;

import android.app.Activity;

import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivtyComponent {
    Activity activity();
}
