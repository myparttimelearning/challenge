package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UseCaseTest {

    private UseCaseTestClass useCase;
    private TestDisposableObserver<Object> testObserver;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private AfterExecutionThread mockAfterExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp(){
        this.useCase = new UseCaseTestClass(mockThreadExecutor, mockAfterExecutionThread);
        this.testObserver = new TestDisposableObserver<>();
        given(mockAfterExecutionThread.getScheduler()).willReturn(new TestScheduler());
    }

    @Test
    public void testCorrectResultReturnedByBuildUseCase(){
        useCase.execute(testObserver, Params.EMPTY);
        assertTrue(testObserver.valuesCount == 0);
        testObserver.onNext("test");
        assertTrue(testObserver.valuesCount == 1);
    }

    @Test
    public void testDisposalOfUseCase(){
        useCase.execute(testObserver, Params.EMPTY);
        useCase.dispose();

        assertTrue(testObserver.isDisposed());
    }

    @Test
    public void testExecutionFailsIfObserverIsNull(){
        expectedException.expect(NullPointerException.class);
        useCase.execute(null, Params.EMPTY);

    }

    private static class UseCaseTestClass extends UseCase<Object, Params> {

        UseCaseTestClass(ThreadExecutor threadExecutor, AfterExecutionThread afterExecutionThread) {
            super(threadExecutor, afterExecutionThread);
        }

        @Override
        Observable<Object> buildUseCaseObservable(Params params) {
            return Observable.empty();
        }

        @Override
        public void execute(DisposableObserver<Object> observer, Params params) {
            super.execute(observer, params);
        }
    }

    private static class Params {
        private static Params EMPTY = new Params();
        private Params() {}
    }

    private static class TestDisposableObserver<T> extends DisposableObserver<T> {
        private int valuesCount = 0;

        @Override public void onNext(T value) {
            valuesCount++;
        }

        @Override public void onError(Throwable e) {
            // no-op by default.
        }

        @Override public void onComplete() {
            // no-op by default.
        }
    }
}