package com.rumodigi.fanduelmobilechallenge.data.entity.mapping;

import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;
import com.rumodigi.fanduelmobilechallenge.domain.models.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * Mapper to transform data layer {@link PlayerEntity} to {@link Player}
 * in the domain layer
 *
 */

@Singleton
public class PlayerEntityDataMap {

    @Inject
    PlayerEntityDataMap(){}

    /**
     * Transform a {@link PlayerEntity} to {@link Player}
     *
     * @param playerEntity to be transformed
     * @return {@link Player} if {@link PlayerEntity} is valid otherwise null
     */

    public Player transform(PlayerEntity playerEntity){
        Player player = null;
        if(playerEntity != null){
            player = new Player(playerEntity.getId());
            player.setFirstName(playerEntity.getFirstName());
            player.setLastName(playerEntity.getLastName());
            player.setFppg(playerEntity.getFppg());
            player.setImageUrl(playerEntity.getImageUrl());
        }
        return player;
    }

    /**
     * Transform Collection of {@link PlayerEntity} into List of {@link Player}.
     *
     * @param playerEntityCollection to be transformed.
     * @return Collection {@link Player} if valid {@link PlayerEntity} otherwise empty list.
     */

    public List<Player> transform(Collection<PlayerEntity> playerEntityCollection){
        final List<Player> playerList = new ArrayList<>();
        for(PlayerEntity playerEntity : playerEntityCollection){
            final Player player = transform(playerEntity);
            if (player != null){
                playerList.add(player);
            }
        }
        return playerList;
    }

}
