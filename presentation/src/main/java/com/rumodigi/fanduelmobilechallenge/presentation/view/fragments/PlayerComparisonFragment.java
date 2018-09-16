package com.rumodigi.fanduelmobilechallenge.presentation.view.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.rumodigi.fanduelmobilechallenge.domain.models.Player;
import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.PlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.presenter.PlayerComparisonPresenter;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerComparisonFragment extends BaseFragment implements PlayerComparisonView {

    @Inject
    PlayerComparisonPresenter playerComparisonPresenter;

    @Bind(R.id.higherButton)
    Button switchPlayerButton;

    @Bind(R.id.firstNamePlayer1)
    TextView firstNamePlayer1;

    @Bind(R.id.firstNamePlayer2)
    TextView firstNamePlayer2;

    @Bind(R.id.lastNamePlayer1)
    TextView lastNamePlayer1;

    @Bind(R.id.lastNamePlayer2)
    TextView lastNamePlayer2;

    @Bind(R.id.fppgPlayer1)
    TextView fppgPlayer1;

    @OnClick(R.id.higherButton)
    void switchPlayers() {
        this.playerComparisonPresenter.switchPlayers();
    }

    public PlayerComparisonFragment() {
        setRetainInstance(true);
    }

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(PlayerComponent.class).inject(this);

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_player_comparison, container, false);
        ButterKnife.bind(this, fragmentView);
        //setupRecyclerView();
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.playerComparisonPresenter.setView(this);
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
    public void renderPlayers(Pair<Player, Player> playerModelPair) {
        firstNamePlayer1.setText(playerModelPair.first.getFirstName());
        lastNamePlayer1.setText(playerModelPair.first.getLastName());
        fppgPlayer1.setText(String.valueOf(playerModelPair.first.getFppg()));
        firstNamePlayer2.setText(playerModelPair.second.getFirstName());
        lastNamePlayer2.setText(playerModelPair.second.getLastName());
    }
    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    /**
     * Loads all players.
     */
    private void loadUserList() {
        this.playerComparisonPresenter.initialise();
    }
}
