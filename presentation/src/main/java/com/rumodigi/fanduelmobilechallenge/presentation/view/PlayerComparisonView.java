package com.rumodigi.fanduelmobilechallenge.presentation.view;

import android.util.Pair;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;

public interface PlayerComparisonView extends LoadDataView{
    /**
     * Render a player list in the UI.
     *
     * @param playerModelPair The {@link Pair} of {@link Player} that will be shown.
     */
    void renderPlayers(Pair<Player, Player> playerModelPair);
    void updateScore(int score);
}
