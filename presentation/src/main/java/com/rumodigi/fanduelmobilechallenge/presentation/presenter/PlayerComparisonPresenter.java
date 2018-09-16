package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.DefaultObserver;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.GetPlayerList;
import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;


@PerActivity
public class PlayerComparisonPresenter implements Presenter {
    private final GetPlayerList getPlayerListUseCase;
    private PlayerComparisonView playerComparisonView;
    private final List<Player> playerList;
    private Player player1;
    private Player player2;
    private Pair<Player, Player> playerPair;
    private int score = 0;
    private boolean fromSavedInstance;
    private String savedInstancePlayer1Id;
    private String savedInstancePlayer2Id;

    @Inject
    public PlayerComparisonPresenter(GetPlayerList getPlayerListUseCase){
        this.playerList = new ArrayList<>();
        this.getPlayerListUseCase = getPlayerListUseCase;
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

    public void initialise(){
        this.loadPlayerList();
    }

    public void initialise(String player1Id, String player2Id, int currentScore){
        fromSavedInstance = true;
        savedInstancePlayer1Id = player1Id;
        savedInstancePlayer2Id = player2Id;
        score = currentScore;
        this.loadPlayerList();
    }

    private void loadPlayerList(){
        this.getPlayerListUseCase.execute(new PlayerListObserver(), null);
    }

    private void refreshDetailsFromSavedInstanceDetails(){
        for(Player player : playerList){
            if (player.getId().equals(savedInstancePlayer1Id)){
                player1 = player;
            } else if (player.getId().equals(savedInstancePlayer2Id)){
                player2 = player;
            }
        }
        playerPair = new Pair<>(getPlayer1(), getPlayer2());
        playerComparisonView.updateScore(score);
        playerComparisonView.renderPlayers(playerPair);
    }

    private void getFirstPair(){
        player1 = getPlayerList().get(getRandomNumberInRange());
        setPlayer2();
        playerComparisonView.renderPlayers(playerPair);
    }

    public void guessedHigher(){
        if (getPlayer2().getFppg() > getPlayer1().getFppg()){
            score++;
        }
        playerComparisonView.updateScore(score);
    }

    public void guessedLower(){
        if (getPlayer2().getFppg() < getPlayer1().getFppg()){
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
        playerComparisonView.renderPlayers(playerPair);
    }

    private void setPlayer2() {
        player2 = getPlayerList().get(getRandomNumberInRange());
        while (playersAreTheSame()){
            player2 = getPlayerList().get(getRandomNumberInRange());
        }
        playerPair = new Pair<>(getPlayer1(), getPlayer2());
        Log.i("Player 1", playerPair.first.getFirstName() + " " + playerPair.first.getLastName());
        Log.i("Player 2", playerPair.second.getFirstName() + " " + playerPair.second.getLastName());
    }

    private boolean playersAreTheSame() {
        return getPlayer2().getId().matches(getPlayer1().getId());
    }

    private int getRandomNumberInRange(){
        return new Random().nextInt(getPlayerList().size());
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    private final class PlayerListObserver extends DefaultObserver<List<Player>> {
        @Override public void onComplete() {
            if(fromSavedInstance){
                PlayerComparisonPresenter.this.refreshDetailsFromSavedInstanceDetails();
            } else {
                PlayerComparisonPresenter.this.getFirstPair();
            }

        }

        @Override public void onError(Throwable e) {

        }

        @Override public void onNext(List<Player> players) {
            getPlayerList().addAll(players);
        }
    }
}
