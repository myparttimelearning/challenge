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
import com.rumodigi.fanduelmobilechallenge.presentation.view.ImageLoaderView;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerComparisonFragment extends BaseFragment implements PlayerComparisonView {

    @Inject
    PlayerComparisonPresenter playerComparisonPresenter;

    @Bind(R.id.currentScore)
    TextView currentScore;

    @Bind(R.id.higherButton)
    Button higherButton;

    @Bind(R.id.lowerButton)
    Button lowerButton;

    @Bind(R.id.player1Photo)
    ImageLoaderView photoPlayer1;

    @Bind(R.id.namePlayer1)
    TextView namePlayer1;

    @Bind(R.id.player2Photo)
    ImageLoaderView photoPlayer2;

    @Bind(R.id.namePlayer2)
    TextView namePlayer2;

    @Bind(R.id.fppgPlayer1)
    TextView fppgPlayer1;

    @Bind(R.id.gameOver)
    TextView gameOver;

    @Bind(R.id.playAgain)
    Button playAgain;

    @OnClick(R.id.higherButton)
    void guessedHigher() {
        this.playerComparisonPresenter.guessedHigher();
    }

    @OnClick(R.id.lowerButton)
    void guessedLower() {
        this.playerComparisonPresenter.guessedLower();
    }

    @OnClick(R.id.playAgain)
    void playAgain(){
        this.playerComparisonPresenter.resetGame();
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
        return fragmentView;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.playerComparisonPresenter.setView(this);
        if (savedInstanceState == null) {
            this.loadPlayers();
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
        photoPlayer1.setImageUrl(playerModelPair.first.getImageUrl());
        namePlayer1.setText(playerModelPair.first.getFirstName() + " " + playerModelPair.first.getLastName());
        fppgPlayer1.setText(String.valueOf(playerModelPair.first.getFppg()));
        photoPlayer2.setImageUrl(playerModelPair.second.getImageUrl());
        namePlayer2.setText(playerModelPair.second.getFirstName() + " " + playerModelPair.second.getLastName());
    }

    @Override
    public void updateScore(int score){
        if(score == 0){
            gameOver.setVisibility(View.GONE);
            playAgain.setVisibility(View.GONE);
            higherButton.setVisibility(View.VISIBLE);
            lowerButton.setVisibility(View.VISIBLE);
        }
        if(score < 10){
            currentScore.setText(Integer.toString(score));
            playerComparisonPresenter.switchPlayers();
        } else {
            currentScore.setText(Integer.toString(score));
            higherButton.setVisibility(View.GONE);
            lowerButton.setVisibility(View.GONE);
            gameOver.setVisibility(View.VISIBLE);
            playAgain.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override public Context context() {
        return this.getActivity().getApplicationContext();
    }

    /**
     * Loads pair of players.
     */
    private void loadPlayers() {
        this.playerComparisonPresenter.initialise();
    }
}
