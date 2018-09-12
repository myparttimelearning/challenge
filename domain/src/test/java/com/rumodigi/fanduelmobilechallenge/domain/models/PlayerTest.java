package com.rumodigi.fanduelmobilechallenge.domain.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private static final String TEST_ID = "12345-67890";

    private Player player;

    @Before
    public void setUp(){
        player = new Player(TEST_ID);
    }

    @Test
    public void testPlayerConstructorSetsId() {
        assertTrue(TEST_ID.equals(player.getId()));
    }
}