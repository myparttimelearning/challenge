package com.rumodigi.fanduelmobilechallenge.presentation;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Singleton
public class UIThread implements AfterExecutionThread {

    @Inject
    UIThread() {}

    @Override public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
