package com.rumodigi.fanduelmobilechallenge.presentation.view;

import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;

import java.util.Collection;

public interface PlayerView extends LoadDataView{

    /**
     * View a {@link PlayerModel} profile/details.
     *
     * @param playerModel The user that will be shown.
     */
    void viewUser(PlayerModel playerModel);
}
