package com.rumodigi.fanduelmobilechallenge.presentation.di.components;

import android.content.Context;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.ApplicationModule;
import com.rumodigi.fanduelmobilechallenge.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    AfterExecutionThread afterExecutionThread();
    PlayerRepository playerRepository();
}
