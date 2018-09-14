package com.rumodigi.fanduelmobilechallenge.presentation.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.PlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;
import com.rumodigi.fanduelmobilechallenge.presentation.presenter.PlayerListPresenter;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerListView;

import java.util.Collection;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class PlayerListFragment extends BaseFragment implements PlayerListView {

    public interface PlayerListListener {
        void onUserClicked(final PlayerModel playerModel);
    }

    @Inject
    PlayerListPresenter playerListPresenter;

    private PlayerListListener playerListListener;

    public PlayerListFragment() {
        setRetainInstance(true);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof PlayerListListener) {
            this.playerListListener = (PlayerListListener) activity;
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PlayerComponent.class).inject(this);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_player_list, container, false);
        ButterKnife.bind(this, fragmentView);
        //setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.playerListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadUserList();
        }
    }

    @Override
    public void showLoading(){}

    /**
     * Hide a loading view.
     */
    @Override
    public void hideLoading(){}

    /**
     * Show a retry view in case of an error when retrieving data.
     */
    @Override
    public void showRetry(){}

    /**
     * Hide a retry view shown if there was an error when retrieving data.
     */
    @Override
    public void hideRetry(){}

    @Override
    public void renderPlayerList(Collection<PlayerModel> userModelCollection) {
//        if (userModelCollection != null) {
//            this.usersAdapter.setUsersCollection(userModelCollection);
//        }
    }

    @Override
    public void viewUser(PlayerModel playerModel) {
//        if (this.userListListener != null) {
//            this.userListListener.onUserClicked(userModel);
//        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    /**
     * Loads all users.
     */
    private void loadUserList() {
        Log.d("PlayerListFragment", "initialising presenter");
        this.playerListPresenter.initialise();
    }
}
