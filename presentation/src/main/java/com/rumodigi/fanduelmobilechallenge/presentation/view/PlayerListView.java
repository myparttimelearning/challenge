package com.rumodigi.fanduelmobilechallenge.presentation.view;

import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

import java.util.Collection;

public interface PlayerListView extends LoadDataView{
    /**
     * Render a player list in the UI.
     *
     * @param playerModelCollection The collection of {@link PlayerModel} that will be shown.
     */
    void renderPlayerList(Collection<PlayerModel> playerModelCollection);

    /**
     * View a {@link PlayerModel} profile/details.
     *
     * @param playerModel The user that will be shown.
     */
    void viewUser(PlayerModel playerModel);
}
