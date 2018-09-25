package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.GetPlayerList;
import com.rumodigi.fanduelmobilechallenge.presentation.mapper.PlayerModelDataMapper;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class PlayerComparisonPresenterTest {

    private static final String FAKE_FIRST_NAME_1 = "Test1";
    private static final String FAKE_LAST_NAME_1 = "Test1";
    private static final String FAKE_ID_1 = "12345-67890";
    private static final double FAKE_FPPG_1 = 10.20303948;
    private static final String FAKE_IMG_URL_1 = "http://www.doesnot.exist/imagetest1.jpg";

    private static final String FAKE_FIRST_NAME_2 = "Test2";
    private static final String FAKE_LAST_NAME_2 = "Test2";
    private static final String FAKE_ID_2 = "23456-78901";
    private static final double FAKE_FPPG_2 = 11.20303948;
    private static final String FAKE_IMG_URL_2 = "http://www.doesnot.exist/imagetest2.jpg";

    private static final String FAKE_FIRST_NAME_3 = "Test3";
    private static final String FAKE_LAST_NAME_3 = "Test3";
    private static final String FAKE_ID_3 = "34567-89012";
    private static final double FAKE_FPPG_3 = 12.20303948;
    private static final String FAKE_IMG_URL_3 = "http://www.doesnot.exist/imagetest3.jpg";

    private static final String FAKE_FIRST_NAME_4 = "Test4";
    private static final String FAKE_LAST_NAME_4 = "Test4";
    private static final String FAKE_ID_4 = "34567-89012";
    private static final double FAKE_FPPG_4 = 9.20303948;
    private static final String FAKE_IMG_URL_4 = "http://www.doesnot.exist/imagetest4.jpg";

    private static List<Player> testPlayerList;
    private static Player player1;
    private static Player player2;
    private static Player player3;
    private static Player player4;

    private static PlayerModel playerModel1;
    private static PlayerModel playerModel2;

    private PlayerComparisonPresenter playerComparisonPresenter;
    private PlayerModelDataMapper playerModelDataMapper;

    @Mock
    PlayerComparisonView mockPlayerComparisonView;
    @Mock
    GetPlayerList mockGetPlayerList;

    @BeforeClass
    public static void onlyOnce() {
        player1 = new Player(FAKE_ID_1);
        player1.setImageUrl(FAKE_IMG_URL_1);
        player1.setFppg(FAKE_FPPG_1);
        player1.setFirstName(FAKE_FIRST_NAME_1);
        player1.setLastName(FAKE_LAST_NAME_1);

        player2 = new Player(FAKE_ID_2);
        player2.setImageUrl(FAKE_IMG_URL_2);
        player2.setFppg(FAKE_FPPG_2);
        player2.setFirstName(FAKE_FIRST_NAME_2);
        player2.setLastName(FAKE_LAST_NAME_2);

        player3 = new Player(FAKE_ID_3);
        player3.setImageUrl(FAKE_IMG_URL_3);
        player3.setFppg(FAKE_FPPG_3);
        player3.setFirstName(FAKE_FIRST_NAME_3);
        player3.setLastName(FAKE_LAST_NAME_3);

        player4 = new Player(FAKE_ID_4);
        player4.setImageUrl(FAKE_IMG_URL_4);
        player4.setFppg(FAKE_FPPG_4);
        player4.setFirstName(FAKE_FIRST_NAME_4);
        player4.setLastName(FAKE_LAST_NAME_4);

        playerModel1 = new PlayerModel(FAKE_ID_1);
        playerModel1.setImageUrl(FAKE_IMG_URL_1);
        playerModel1.setFppg(FAKE_FPPG_1);
        playerModel1.setName(FAKE_FIRST_NAME_1 + " " + FAKE_LAST_NAME_1);

        playerModel2 = new PlayerModel(FAKE_ID_2);
        playerModel2.setImageUrl(FAKE_IMG_URL_2);
        playerModel2.setFppg(FAKE_FPPG_2);
        playerModel2.setName(FAKE_FIRST_NAME_2 + " " + FAKE_LAST_NAME_2);

        testPlayerList = new ArrayList<>();
        testPlayerList.add(player1);
        testPlayerList.add(player2);
        testPlayerList.add(player3);
        testPlayerList.add(player4);
    }

    @Before
    public void setUp() throws Exception {
        playerModelDataMapper = new PlayerModelDataMapper();
        playerComparisonPresenter = new PlayerComparisonPresenter(mockGetPlayerList, playerModelDataMapper);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInitialise_confirmObservableIsCalled(){
        playerComparisonPresenter.initialise();
        verify(mockGetPlayerList, times(1)).execute(any(DisposableObserver.class), any());
    }

    @Test
    public void testInitialiseSavedInstance_confirmObservableIsCalled_checkMemberVariablesAreSet(){
        playerComparisonPresenter.initialise(playerModel1, playerModel2, 3);
        assertThat(playerComparisonPresenter.getPlayer1(), is(playerModel1));
        assertThat(playerComparisonPresenter.getPlayer2(), is(playerModel2));
        verify(mockGetPlayerList, times(1)).execute(any(DisposableObserver.class), any());
    }

    @Test
    public void testCallbackFromPlayerListObserver_confirmUniqueFirstPairOfPlayerModelsAreSet(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.playerList(testPlayerList);
        verify(mockPlayerComparisonView, times(1)).renderPlayers(any(PlayerModel.class), any(PlayerModel.class));
        assertFalse(playerComparisonPresenter.getPlayer1().getId().equals(playerComparisonPresenter.getPlayer2().getId()));
    }

    @Test
    public void testCallbackFromPlayerListObserver_confirmSavedInstanceIsReinstated(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel1, playerModel2, 3);
        playerComparisonPresenter.playerList(testPlayerList);
        assertThat(playerComparisonPresenter.getPlayer1(), is(playerModel1));
        assertThat(playerComparisonPresenter.getPlayer2(), is(playerModel2));
        verify(mockPlayerComparisonView, times(1)).updateScore(3);
        verify(mockPlayerComparisonView, times(1)).renderPlayers(any(PlayerModel.class), any(PlayerModel.class));
    }

    @Test
    public void testCheckGuessedLowerIsCorrect_confirmUpdateScoreIsCalledWithCorrectScore(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel1, playerModel2, 3);
        playerComparisonPresenter.guessedLower();
        verify(mockPlayerComparisonView, times(1)).updateScore(4);
    }

    @Test
    public void testCheckGuessedLowerIsWrong_confirmUpdateScoreIsCalledWithCorrectScore(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel2, playerModel1, 3);
        playerComparisonPresenter.guessedLower();
        verify(mockPlayerComparisonView, times(1)).updateScore(3);
    }

    @Test
    public void testCheckGuessedHigherIsWrong_confirmUpdateScoreIsCalledWithCorrectScore(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel1, playerModel2, 3);
        playerComparisonPresenter.guessedHigher();
        verify(mockPlayerComparisonView, times(1)).updateScore(3);
    }

    @Test
    public void testCheckGuessedHigherIsCorrect_confirmUpdateScoreIsCalledWithCorrectScore(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel2, playerModel1, 3);
        playerComparisonPresenter.guessedHigher();
        verify(mockPlayerComparisonView, times(1)).updateScore(4);
    }

    @Test
    public void testCheckGuessedHigherIsIgnoredWhenPlayer2IsNull_confirmUpdateScoreIsNeverCalled(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel1, null, 2);
        playerComparisonPresenter.guessedHigher();
        verifyNoMoreInteractions(mockPlayerComparisonView);
    }

    @Test
    public void testCheckGuessedHigherIsIgnoredWhenPlayer1IsNull_confirmUpdateScoreIsNeverCalled(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(null, playerModel2, 2);
        playerComparisonPresenter.guessedHigher();
        verifyNoMoreInteractions(mockPlayerComparisonView);
    }

    @Test
    public void testCheckGuessedLowerIsIgnoredWhenPlayer2IsNull_confirmUpdateScoreIsNeverCalled(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel1, null, 2);
        playerComparisonPresenter.guessedLower();
        verifyNoMoreInteractions(mockPlayerComparisonView);
    }

    @Test
    public void testCheckGuessedLowerIsIgnoredWhenPlayer1IsNull_confirmUpdateScoreIsNeverCalled(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(null, playerModel2, 2);
        playerComparisonPresenter.guessedLower();
        verifyNoMoreInteractions(mockPlayerComparisonView);
    }

    @Test
    public void testCheckGuessedHigherIsIgnoredWhenPlayersAreNull_confirmUpdateScoreIsNeverCalled(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise();
        playerComparisonPresenter.guessedHigher();
        verifyNoMoreInteractions(mockPlayerComparisonView);
    }

    @Test
    public void testSwitchPlayers_confirmPlayer2BecomesPlayer1(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.playerList(testPlayerList);
        verify(mockPlayerComparisonView, times(1)).renderPlayers(any(PlayerModel.class), any(PlayerModel.class));
        assertFalse(playerComparisonPresenter.getPlayer1().getId().equals(playerComparisonPresenter.getPlayer2().getId()));
        String player2Id = playerComparisonPresenter.getPlayer2().getId();
        playerComparisonPresenter.switchPlayers();
        assertTrue(playerComparisonPresenter.getPlayer1().getId().equals(player2Id));
        assertFalse(playerComparisonPresenter.getPlayer1().getId().equals(playerComparisonPresenter.getPlayer2().getId()));
    }

    @Test
    public void testResetGame_confirmScoreSetBackToZero(){
        playerComparisonPresenter.setView(mockPlayerComparisonView);
        playerComparisonPresenter.initialise(playerModel2, playerModel1, 3);
        playerComparisonPresenter.resetGame();
        verify(mockPlayerComparisonView, times(1)).updateScore(0);
    }
}