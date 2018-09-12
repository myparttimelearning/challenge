package com.rumodigi.fanduelmobilechallenge.domain.executors;

import java.util.concurrent.Executor;

/**
 * Make usecases (iteractors) run in background by using a worker thread executor.
 *
 * {@link com.rumodigi.fanduelmobilechallenge.domain.usecases.UseCase} out of the UI thread.
 */

public interface ThreadExecutor extends Executor {}
