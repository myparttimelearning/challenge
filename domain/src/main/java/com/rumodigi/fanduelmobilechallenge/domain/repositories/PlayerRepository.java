package com.rumodigi.fanduelmobilechallenge.domain.repositories;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;

import java.util.List;

import io.reactivex.Observable;

/**
 *
 * Interface representing a repository of {@link Player} data
 *
 */


public interface PlayerRepository {

    /**
     *
     * Get a list of {@link Player} emitted from an {@link Observable}
     *
     */

    Observable<List<Player>> players();

    /**
     *
     * Get a {@link Player} emitted by an {@link Observable}
     *
     */

    Observable<Player> player(final String id);
}
