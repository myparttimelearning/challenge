package com.rumodigi.fanduelmobilechallenge.data.repository.datastores;

import com.rumodigi.fanduelmobilechallenge.data.connect.RestApi;
import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;

import java.util.List;

import io.reactivex.Observable;

public class OnlinePlayerDataStore implements PlayerDataStore {

    private final RestApi restApi;

    OnlinePlayerDataStore(RestApi restApi){
        this.restApi = restApi;
    }

    @Override
    public Observable<List<PlayerEntity>> playerEntityList(){
        return this.restApi.playerEntityList();
    }
}
