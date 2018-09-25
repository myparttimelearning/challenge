package com.rumodigi.fanduelmobilechallenge.presentation.presenter;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;

import java.util.List;

interface PlayerListObserverInterface {
    void playerList(List<Player> players);
}
