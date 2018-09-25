package com.rumodigi.fanduelmobilechallenge.presentation.mapper;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class PlayerModelDataMapperTest {

    private static final String FAKE_FIRST_NAME = "Russell";
    private static final String FAKE_LAST_NAME = "Morris";
    private static final String FAKE_ID = "12345-67890";
    private static final double FAKE_FPPG = 10.20303948;
    private static final String FAKE_IMG_URL = "http://www.doesnot.exist/imagetest.jpg";

    private PlayerModelDataMapper playerModelDataMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        playerModelDataMapper = new PlayerModelDataMapper();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTransformPlayer(){
        Player player = new Player(FAKE_ID);
        player.setLastName(FAKE_LAST_NAME);
        player.setFirstName(FAKE_FIRST_NAME);
        player.setFppg(FAKE_FPPG);
        player.setImageUrl(FAKE_IMG_URL);
        PlayerModel playerModel = playerModelDataMapper.transform(player);

        assertThat(playerModel.getName(), is(FAKE_FIRST_NAME + " " + FAKE_LAST_NAME));
        assertThat(playerModel.getFppg(), is(FAKE_FPPG));
        assertThat(playerModel.getId(), is(FAKE_ID));
        assertThat(playerModel.getImageUrl(), is(FAKE_IMG_URL));

    }

    @Test
    public void testTransformPlayerNotValidifNull() {
        expectedException.expect(IllegalArgumentException.class);
        playerModelDataMapper.transform(null);
    }
}