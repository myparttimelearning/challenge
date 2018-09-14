package com.rumodigi.fanduelmobilechallenge.data.repository.datastores;

import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */

public interface PlayerDataStore {
    /**
     * Get an {@link Observable} which will emit a List of {@link PlayerEntity}.
     */
    Observable<List<PlayerEntity>> playerEntityList();
}
