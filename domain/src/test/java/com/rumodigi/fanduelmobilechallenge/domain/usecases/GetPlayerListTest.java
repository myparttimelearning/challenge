package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetPlayerListTest {

    private GetPlayerList getPlayerList;

    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private AfterExecutionThread mockAfterExecutionThread;
    @Mock
    private PlayerRepository mockPlayerRepository;

    @Before
    public void setUp() {

        getPlayerList = new GetPlayerList(mockPlayerRepository,
                mockThreadExecutor,
                mockAfterExecutionThread);
    }

    @Test
    public void testGetPlayerListUseCaseSuccess(){
        getPlayerList.buildUseCaseObservable(null);
        verify(mockPlayerRepository).players();
        verifyNoMoreInteractions(mockPlayerRepository);
        verifyZeroInteractions(mockAfterExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}