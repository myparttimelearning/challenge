package com.rumodigi.fanduelmobilechallenge.domain.usecases;

import com.rumodigi.fanduelmobilechallenge.domain.executors.AfterExecutionThread;
import com.rumodigi.fanduelmobilechallenge.domain.executors.ThreadExecutor;
import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.domain.repositories.PlayerRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 *
 * Implementation of {@link UseCase} that represents a use case for
 * getting data related to a specific {@link Player}.
 *
 */


class GetPlayerDetails extends UseCase<Player, GetPlayerDetails.Params> {

    private final PlayerRepository playerRepository;

    @Inject
    GetPlayerDetails(PlayerRepository playerRepository,
                     ThreadExecutor threadExecutor,
                     AfterExecutionThread afterExecutionThread){
        super(threadExecutor, afterExecutionThread);
        this.playerRepository = playerRepository;
    }

    @Override
    Observable<Player> buildUseCaseObservable(Params params) {
        return this.playerRepository.player(params.id);
    }

    public static class Params
    {
        private final String id;

        private Params(String id){
            this.id = id;
        }

        public static Params forPlayer(String id){
            return new Params(id);
        }
    }
}
