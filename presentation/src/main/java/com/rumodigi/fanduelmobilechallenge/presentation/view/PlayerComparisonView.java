package com.rumodigi.fanduelmobilechallenge.presentation.view;

import android.util.Pair;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

public interface PlayerComparisonView extends LoadDataView{
    /**
     * Render a player list in the UI.
     *
     * @param {The player objects of {@link PlayerModel} that will be shown.
     */
    void renderPlayers(PlayerModel player1, PlayerModel player2);
    void updateScore(int score);
}
