package com.rumodigi.fanduelmobilechallenge.data.entity.mapping;

import com.google.gson.annotations.SerializedName;
import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;
import com.rumodigi.fanduelmobilechallenge.domain.models.Player;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class PlayerEntityDataMapTest {

    private static final String FAKE_PLAYER_ID = "12345-67890";
    private static final String FAKE_FIRST_NAME = "Fred";
    private static final String FAKE_LAST_NAME = "Perry";

    private PlayerEntityDataMap playerEntityDataMap;

    @Before
    public void setUp(){
        playerEntityDataMap = new PlayerEntityDataMap();
    }

    @Test
    public void testTransformPlayerEntity(){
        PlayerEntity playerEntity = createFakePlayerEntity();
        Player player = playerEntityDataMap.transform(playerEntity);

        assertThat(player, is(instanceOf(Player.class)));
        assertThat(player.getId(), is(FAKE_PLAYER_ID));
        assertThat(player.getFirstName(), is(FAKE_FIRST_NAME));
        assertThat(player.getLastName(), is(FAKE_LAST_NAME));
    }

    @Test
    public void testTransformPlayerEntityCollection() {
        PlayerEntity mockPlayerEntityOne = mock(PlayerEntity.class);
        PlayerEntity mockPlayerEntityTwo = mock(PlayerEntity.class);

        List<PlayerEntity> playerEntityList = new ArrayList<>(5);
        playerEntityList.add(mockPlayerEntityOne);
        playerEntityList.add(mockPlayerEntityTwo);

        Collection<Player> playerCollection = playerEntityDataMap.transform(playerEntityList);

        assertThat(playerCollection.toArray()[0], is(instanceOf(Player.class)));
        assertThat(playerCollection.toArray()[1], is(instanceOf(Player.class)));
        assertThat(playerCollection.size(), is(2));
    }



    private PlayerEntity createFakePlayerEntity() {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(FAKE_PLAYER_ID);
        playerEntity.setFirstName(FAKE_FIRST_NAME);
        playerEntity.setLastName(FAKE_LAST_NAME);

        return playerEntity;
    }

}