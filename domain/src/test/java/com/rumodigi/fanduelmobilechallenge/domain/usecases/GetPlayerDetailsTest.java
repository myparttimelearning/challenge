package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetPlayerDetailsTest {

    private static final String TEST_ID = "12345-67890";
    private GetPlayerDetails getPlayerDetails;

    @Mock
    private PlayerRepository mockPlayerRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private AfterExecutionThread mockAfterExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        getPlayerDetails = new GetPlayerDetails(mockPlayerRepository,
                mockThreadExecutor,
                mockAfterExecutionThread);
    }

    @Test
    public void testGetPlayerDetailsUseCaseObservableSuccess(){
        getPlayerDetails.buildUseCaseObservable(GetPlayerDetails.Params.forPlayer(TEST_ID));
        verify(mockPlayerRepository).player(TEST_ID);
        verifyNoMoreInteractions(mockPlayerRepository);
        verifyZeroInteractions(mockAfterExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }

    @Test
    public void testNoOrEmptyParametersFails(){
        expectedException.expect(NullPointerException.class);
        getPlayerDetails.buildUseCaseObservable(null);
    }
}