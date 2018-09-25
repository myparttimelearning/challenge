package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.GetPlayerList;
import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.mapper.PlayerModelDataMapper;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;


@PerActivity
public class PlayerComparisonPresenter implements Presenter, PlayerListObserverInterface {

    private PlayerComparisonView playerComparisonView;
    private final GetPlayerList getPlayerListUseCase;
    private final PlayerModelDataMapper playerModelDataMapper;
    private final List<PlayerModel> playerList;
    private PlayerModel player1;
    private PlayerModel player2;
    private int score = 0;
    private boolean fromSavedInstance;

    @Inject
    public PlayerComparisonPresenter(GetPlayerList getPlayerListUseCase,
                                     PlayerModelDataMapper playerModelDataMapper){
        this.playerList = new ArrayList<>();
        this.getPlayerListUseCase = getPlayerListUseCase;
        this.playerModelDataMapper = playerModelDataMapper;
    }

    public void setView(@NonNull PlayerComparisonView view) {
        this.playerComparisonView = view;
    }

    @Override
    public void resume(){}

    @Override
    public void pause(){}

    @Override
    public void destroy(){
        this.getPlayerListUseCase.dispose();
        this.playerComparisonView = null;
    }

    @Override
    public void playerList(List<Player> players) {
        for(Player player : players){
            playerList.add(mapPlayerToPlayerModel(player));
        }
        if(fromSavedInstance){
            refreshDetailsFromSavedInstanceDetails();
        } else {
            getFirstPair();
        }
    }

    public void initialise(){
        this.loadPlayerList();
    }

    public void initialise(PlayerModel player1, PlayerModel player2, int currentScore){
        fromSavedInstance = true;
        this.player1 = player1;
        this.player2 = player2;
        score = currentScore;
        this.loadPlayerList();
    }

    public void guessedHigher(){
        if(getPlayer1() == null || getPlayer2() == null){
            return;
        }
        if (getPlayer1().getFppg() > getPlayer2().getFppg()){
            score++;
        }
        playerComparisonView.updateScore(score);
    }

    public void guessedLower(){
        if(getPlayer1() == null || getPlayer2() == null){
            return;
        }
        if (getPlayer1().getFppg() < getPlayer2().getFppg()){
            score++;
        }
        playerComparisonView.updateScore(score);
    }

    public void resetGame(){
        score = 0;
        playerComparisonView.updateScore(score);
    }

    public void switchPlayers() {
        player1 = getPlayer2();
        setPlayer2();
        playerComparisonView.renderPlayers(getPlayer1(), getPlayer2());
    }

    public PlayerModel getPlayer1() {
        return player1;
    }

    public PlayerModel getPlayer2() {
        return player2;
    }

    private void loadPlayerList(){
        this.getPlayerListUseCase.execute(new PlayerListObserver(this), null);
    }

    private void getFirstPair(){
        player1 = playerList.get(getRandomNumberInRange());
        setPlayer2();
        playerComparisonView.renderPlayers(getPlayer1(), getPlayer2());
    }

    private void refreshDetailsFromSavedInstanceDetails(){
        playerComparisonView.updateScore(score);
        playerComparisonView.renderPlayers(getPlayer1(), getPlayer2());
    }

    private PlayerModel mapPlayerToPlayerModel(Player player){
        return this.playerModelDataMapper.transform(player);
    }

    private void setPlayer2() {
        player2 = playerList.get(getRandomNumberInRange());
        while (playersAreTheSame()){
            player2 = playerList.get(getRandomNumberInRange());
        }
    }

    private boolean playersAreTheSame() {
        return getPlayer2().getId().matches(getPlayer1().getId());
    }

    private int getRandomNumberInRange(){
        return new Random().nextInt(playerList.size());
    }
}
