package com.rumodigi.fanduelmobilechallenge.data.entity.mapping;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class PlayerEntityJsonMap {

    private final Gson gson;

    @Inject
    public PlayerEntityJsonMap() {
        this.gson = new Gson();
    }

    /**
     *
     * @param playerJson representing a player profile
     * @return {@link PlayerEntity}
     * @throws JsonSyntaxException if the json string is not a valid json structure
     */

    public PlayerEntity transformPlayerEntity(String playerJson) throws JsonSyntaxException{
        final Type playerEntityType = new TypeToken<PlayerEntity>() {}.getType();
        return this.gson.fromJson(playerJson, playerEntityType);
    }

    /**
     *
     * @param playerListJson representing a collection of players
     * @return List of {@link PlayerEntity}
     * @throws JsonSyntaxException if the json string is not a valid json structure
     */

    public List<PlayerEntity> transformPlayerEntityCollection(String playerListJson)
            throws JsonSyntaxException {
        final Type listOfPlayerEntityType = new TypeToken<List<PlayerEntity>>() {}.getType();

        return this.gson.fromJson(playerListJson, listOfPlayerEntityType);
    }

}
