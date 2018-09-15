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
    private Player player1, player2;
    private Pair<Player, Player> playerPair;

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

    private void loadPlayerList(){
        this.getPlayerListUseCase.execute(new PlayerListObserver(), null);
    }

    private void hideViewLoading() {
        this.playerComparisonView.hideLoading();
    }

    private void getFirstPair(){
        player1 = playerList.get(getRandomNumberInRange());
        setPlayer2();
    }

    public void switchPlayers() {
        player1 = player2;
        setPlayer2();
    }

    private void setPlayer2() {
        player2 = playerList.get(getRandomNumberInRange());
        while (playersAreTheSame()){
            player2 = playerList.get(getRandomNumberInRange());
        }
        playerPair = new Pair<>(player1, player2);
        Log.i("Player 1", player1.getFirstName() + " " + player1.getLastName());
        Log.i("Player 2", player2.getFirstName() + " " + player2.getLastName());
    }

    private boolean playersAreTheSame() {
        return player2.getId().matches(player1.getId());
    }

    private int getRandomNumberInRange(){
        return new Random().nextInt(playerList.size());
    }

    private final class PlayerListObserver extends DefaultObserver<List<Player>> {
        @Override public void onComplete() {
            PlayerComparisonPresenter.this.hideViewLoading();
            PlayerComparisonPresenter.this.getFirstPair();
        }

        @Override public void onError(Throwable e) {

        }

        @Override public void onNext(List<Player> players) {
            playerList.addAll(players);
        }
    }
}
