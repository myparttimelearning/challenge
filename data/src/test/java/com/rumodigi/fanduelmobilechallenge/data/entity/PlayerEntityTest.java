package com.rumodigi.fanduelmobilechallenge.data.entity;

import com.google.gson.JsonSyntaxException;
import com.rumodigi.fanduelmobilechallenge.data.entity.mapping.PlayerEntityJsonMap;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PlayerEntityTest {

    private static final String PLAYER_DETAILS_JSON = "{\n"
            + "\"first_name\": \"Stephen\",\n"
            + "\"fppg\": 47.94303797468354,\n"
            + "\"id\": \"15475-9524\",\n"
            + "\"images\": {\n"
            + "\"default\": {\n"
            + "\"url\": \"https://d17odppiik753x.cloudfront.net/playerimages/nba/9524.png\"\n"
            + "}\n"
            + "},\n"
            + "\"last_name\": \"Curry\"\n"
            + "}";

    private static final String PLAYER_COLLECTION_JSON = "{\n"
            + "\"players\":[{\n"
            + "\"first_name\": \"Stephen\",\n"
            + "\"fppg\": 47.94303797468354,\n"
            + "\"id\": \"15475-9524\",\n"
            + "\"images\": {\n"
            + "\"default\": {\n"
            + "\"url\": \"https://d17odppiik753x.cloudfront.net/playerimages/nba/9524.png\"\n"
            + "}\n"
            + "},\n"
            + "\"last_name\": \"Curry\"\n"
            + "},\n"
            +"{\n"
            + "\"first_name\": \"Draymond\",\n"
            + "\"fppg\": 38.9604938271605,\n"
            + "\"id\": \"15475-15860\",\n"
            + "\"images\": {\n"
            + "\"default\": {\n"
            + "\"url\": \"https://d17odppiik753x.cloudfront.net/playerimages/nba/15860.png\"\n"
            + "}\n"
            + "},\n"
            + "\"last_name\": \"Green\"\n"
            + "},\n"
            +"{\n"
            + "\"first_name\": \"Damian\",\n"
            + "\"fppg\": 39.37866666666667,\n"
            + "\"id\": \"15475-20848\",\n"
            + "\"images\": {\n"
            + "\"default\": {\n"
            + "\"url\": \"https://d17odppiik753x.cloudfront.net/playerimages/nba/20848.png\"\n"
            + "}\n"
            + "},\n"
            + "\"last_name\": \"Lillard\"\n"
            + "}]\n"
            + "}";

    private PlayerEntityJsonMap playerEntityJsonMap;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        playerEntityJsonMap = new PlayerEntityJsonMap();
    }

    @Test
    public void testTransformPlayerEntitySuccess() {
        PlayerEntity playerEntity = playerEntityJsonMap.transformPlayerEntity(PLAYER_DETAILS_JSON);
        assertThat(playerEntity.getFirstName(), is(equalTo("Stephen")));
        assertThat(playerEntity.getFppg(), is(47.94303797468354));
        assertThat(playerEntity.getId(), is(equalTo("15475-9524")));
        assertThat(playerEntity.getImageUrl(), is(equalTo("https://d17odppiik753x.cloudfront.net/playerimages/nba/9524.png")));
        assertThat(playerEntity.getLastName(), is(equalTo("Curry")));
    }

    @Test
    public void testTransformPlayerEntityCollectionSuccess() {
        Collection<PlayerEntity> playerEntityCollection =
                playerEntityJsonMap.transformPlayerEntityCollection(
                        PLAYER_COLLECTION_JSON);

        assertThat(((PlayerEntity) playerEntityCollection.toArray()[1]).getId(), is(equalTo("15475-15860")));
        assertThat(((PlayerEntity) playerEntityCollection.toArray()[2]).getId(), is(equalTo("15475-20848")));
        assertThat(playerEntityCollection.size(), is(3));
    }

    @Test
    public void testTransformPlayerEntityNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
       playerEntityJsonMap.transformPlayerEntity("foo");
    }

    @Test
    public void testTransformPlayerEntityCollectionNotValidResponse() {
        expectedException.expect(JsonSyntaxException.class);
        playerEntityJsonMap.transformPlayerEntityCollection("bar");
    }

}