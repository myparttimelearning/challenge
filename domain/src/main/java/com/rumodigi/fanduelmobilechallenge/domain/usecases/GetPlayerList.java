package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 *
 * Implementation of {@link UseCase} representing a use case for
 * retrieving a collection of {@link Player} instances.
 *
 */

class GetPlayerList extends UseCase<List<Player>, Void> {

    private final PlayerRepository playerRepository;

    @Inject
    GetPlayerList(PlayerRepository playerRepository, ThreadExecutor threadExecutor,
                AfterExecutionThread afterExecutionThread) {
        super(threadExecutor, afterExecutionThread);
        this.playerRepository = playerRepository;
    }

    @Override
    Observable<List<Player>> buildUseCaseObservable(Void unused) {
        return this.playerRepository.players();
    }
}
