package com.rumodigi.fanduelmobilechallenge.data.repository.datastores;

import android.content.Context;


import com.rumodigi.fanduelmobilechallenge.data.connect.RestApi;
import com.rumodigi.fanduelmobilechallenge.data.connect.RestApiImpl;
import com.rumodigi.fanduelmobilechallenge.data.entity.mapping.PlayerEntityJsonMap;
import com.rumodigi.fanduelmobilechallenge.data.repository.datastores.OnlinePlayerDataStore;
import com.rumodigi.fanduelmobilechallenge.data.repository.datastores.PlayerDataStore;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;

@Singleton
public class PlayerDataStoreFactory {
    private final Context context;

    @Inject
    PlayerDataStoreFactory(@NonNull Context context){
        this.context = context;
    }

    public PlayerDataStore createOnlineDataStore(){
        final PlayerEntityJsonMap playerEntityJsonMap = new PlayerEntityJsonMap();
        final RestApi restApi = new RestApiImpl(this.context, playerEntityJsonMap);
        return new OnlinePlayerDataStore(restApi);
    }
}
