package com.rumodigi.fanduelmobilechallenge.presentation.view;

import android.util.Pair;

import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

public interface PlayerComparisonView extends LoadDataView{
    /**
     * Render a player list in the UI.
     *
     * @param playerModelPair The {@link Pair} of {@link PlayerModel} that will be shown.
     */
    void renderPlayerList(Pair<PlayerModel, PlayerModel> playerModelPair);
}
