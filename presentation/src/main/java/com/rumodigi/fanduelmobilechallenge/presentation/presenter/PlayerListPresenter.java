package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.DefaultObserver;
import com.rumodigi.fanduelmobilechallenge.domain.usecases.GetPlayerList;
import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerListView;

import java.util.List;

import javax.inject.Inject;


@PerActivity
public class PlayerListPresenter {
    private final GetPlayerList getPlayerListUseCase;

    @Inject
    public PlayerListPresenter(GetPlayerList getPlayerListUseCase){
        this.getPlayerListUseCase = getPlayerListUseCase;
    }

    public void setView(@NonNull PlayerListView view) {
        //this.viewListView = view;
    }

    public void initialise(){
        this.loadPlayerList();
    }

    private void loadPlayerList(){
        Log.d("PlayerListPresenter", "execting player list use case");
        this.getPlayerListUseCase.execute(new PlayerListObserver(), null);
    }

    private final class PlayerListObserver extends DefaultObserver<List<Player>> {
        @Override public void onComplete() {

        }

        @Override public void onError(Throwable e) {

        }

        @Override public void onNext(List<Player> players) {
            for (Player player : players){
                Log.d("Player ID: ", player.getId());
            }
        }
    }
}
