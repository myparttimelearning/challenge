package com.rumodigi.fanduelmobilechallenge.domain.executors;

import io.reactivex.Scheduler;

/**
 * Abstracted thread used to change execution context from one to another. Useful for UI threads
 * for example. UI thread can be encapsulated, as a job is done in the background. Implementing this
 * interface will chage context and update the UI.
 *
 */

public interface AfterExecutionThread {
    Scheduler getScheduler();
}
