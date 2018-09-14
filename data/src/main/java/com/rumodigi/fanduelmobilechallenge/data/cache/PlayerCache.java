package com.rumodigi.fanduelmobilechallenge.data.cache;

import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;

import io.reactivex.Observable;

public interface PlayerCache {
    /**
     *Gets an {@link Observable} which will emit a {@link PlayerEntity}.
     *
     * @param id to retrieve the player
     *
     */
    Observable<PlayerEntity> get(final String id);

    /**
     * Puts entity into the cache.
     *
     * @param playerEntity
     */
    void put(PlayerEntity playerEntity);

    /**
     * Checks if an entity (Player) exists in the cache.
     *
     * @param id
     *
     */
    boolean isCached(final String id);

    /**
     * Checks if the cache is expired.
     *
     */
    boolean isExpired();

    /**
     * Remove everything from the cache
     */

    void clearCache();
}
