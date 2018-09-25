package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.DefaultObserver;

import java.util.List;

public class PlayerListObserver extends DefaultObserver<List<Player>> {

    private PlayerListObserverInterface playerListObserverInterface;

    public PlayerListObserver(PlayerListObserverInterface playerListObserverInterface){
        this.playerListObserverInterface = playerListObserverInterface;
    }

    @Override public void onComplete() {
    }

    @Override public void onError(Throwable e) {

    }

    @Override public void onNext(List<Player> players) {
        playerListObserverInterface.playerList(players);
    }
}
