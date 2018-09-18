package com.rumodigi.fanduelmobilechallenge.presentation.mapper;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

import javax.inject.Inject;

@PerActivity
public class PlayerModelDataMapper {
    @Inject
    public PlayerModelDataMapper(){}

    public PlayerModel transform(Player player){
        if(player == null){
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final PlayerModel playerModel = new PlayerModel(player.getId());
        playerModel.setName(player.getFirstName() + " " + player.getLastName());
        playerModel.setFppg(player.getFppg());
        playerModel.setImageUrl(player.getImageUrl());
        return playerModel;
    }
}
