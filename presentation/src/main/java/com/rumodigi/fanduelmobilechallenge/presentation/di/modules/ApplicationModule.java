package com.rumodigi.fanduelmobilechallenge.presentation.di.modules;

import android.content.Context;

import com.rumodigi.fanduelmobilechallenge.data.cache.PlayerCache;
import com.rumodigi.fanduelmobilechallenge.data.exectuor.TaskExecutor;
import com.rumodigi.fanduelmobilechallenge.data.repository.PlayerDataRepository;
import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;
import com.rumodigi.fanduelmobilechallenge.presentation.AndroidApplication;
import com.rumodigi.fanduelmobilechallenge.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {

        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(TaskExecutor taskExecutor) {
        return taskExecutor;
    }

    @Provides
    @Singleton
    AfterExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    PlayerCache providePlayerCache() {
        return null;
    }

    @Provides
    @Singleton
    PlayerRepository providePlayerRepository(PlayerDataRepository playerDataRepository) {
        return playerDataRepository;
    }
}
