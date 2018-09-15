package com.rumodigi.fanduelmobilechallenge.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerGeneralEntity {
    @SerializedName("players")
    private
    List<PlayerEntity> playerEntityList;

    public List<PlayerEntity> getPlayerEntityList() {
        return playerEntityList;
    }
}
