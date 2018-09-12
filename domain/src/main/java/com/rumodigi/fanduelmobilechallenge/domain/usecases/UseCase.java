package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {

    /**
     * This is an interactor in terms of Clean Architecture.
     * It represents an unit of execution for different use cases, all use cases in the
     * application should implement this contract.
     *
     * Each UseCase implementation will return the result using {@link DisposableObserver}
     * which will execute any work in a background thread then post the result to the UI.
     */

    private final ThreadExecutor threadExecutor;
    private final AfterExecutionThread afterExecutionThread;
    private final CompositeDisposable disposables;

    UseCase(ThreadExecutor threadExecutor, AfterExecutionThread afterExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.afterExecutionThread = afterExecutionThread;
        this.disposables = new CompositeDisposable();
    }

    /**
     * {@link Observable} created, used when executing the {@link UseCase}.
     */

    abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Executes the current use case.
     *
     * @param observer is a {@link DisposableObserver} that listens to the observable build
     * by {@link #buildUseCaseObservable(Params)} ()} method
     * @param params Parameters (Optional) used to build/execute this use case.
     */

    public void execute(DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(afterExecutionThread.getScheduler());
        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    private void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }


}
