package com.rumodigi.fanduelmobilechallenge.data.repository;

import com.rumodigi.fanduelmobilechallenge.data.entity.mapping.PlayerEntityDataMap;
import com.rumodigi.fanduelmobilechallenge.data.repository.datastores.PlayerDataStore;
import com.rumodigi.fanduelmobilechallenge.data.repository.datastores.PlayerDataStoreFactory;
import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class PlayerDataRepository implements PlayerRepository {
    private final PlayerDataStoreFactory playerDataStoreFactory;
    private final PlayerEntityDataMap playerEntityDataMap;

    @Inject
    PlayerDataRepository(PlayerDataStoreFactory playerDataStoreFactory,
                         PlayerEntityDataMap playerEntityDataMap){
        this.playerDataStoreFactory = playerDataStoreFactory;
        this.playerEntityDataMap = playerEntityDataMap;
    }

    @Override
    public Observable<List<Player>> players(){
        final PlayerDataStore playerDataStore = this.playerDataStoreFactory.createOnlineDataStore();
        return  playerDataStore.playerEntityList().map(this.playerEntityDataMap :: transform);
    }

    @Override
    public Observable<Player> player(final String id){
        // TODO: revisit whether or not I need this at all
        return null;
    }

}
