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

import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.PlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.model.PlayerModel;
import com.rumodigi.fanduelmobilechallenge.presentation.presenter.PlayerComparisonPresenter;
import com.rumodigi.fanduelmobilechallenge.presentation.view.ImageLoaderView;
import com.rumodigi.fanduelmobilechallenge.presentation.view.PlayerComparisonView;
import com.rumodigi.fanduelmobilechallenge.presentation.view.activity.PlayerComparisonActivity;


import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlayerComparisonFragment extends BaseFragment implements PlayerComparisonView {

    private static final String INSTANCE_STATE_PARAM_PLAYER_1 = "STATE_PARAM_PLAYER_1";
    private static final String INSTANCE_STATE_PARAM_PLAYER_2 = "STATE_PARAM_PLAYER_2";
    private static final String INSTANCE_STATE_PARAM_CURRENT_SCORE = "STATE_PARAM_CURRENT_SCORE";
    private boolean savedInstanceReload;

    @Inject
    PlayerComparisonPresenter playerComparisonPresenter;

    @Bind(R.id.currentScore)
    TextView currentScore;

    @Bind(R.id.higherButton)
    Button higherButton;

    @Bind(R.id.higherOrLower)
    TextView higherOrLower;

    @Bind(R.id.fppg)
    TextView fppg;

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
        } else {
            this.savedInstanceReload = true;
            this.playerComparisonPresenter.initialise(savedInstanceState.getParcelable(INSTANCE_STATE_PARAM_PLAYER_1),
                    savedInstanceState.getParcelable(INSTANCE_STATE_PARAM_PLAYER_2),
                    savedInstanceState.getInt(INSTANCE_STATE_PARAM_CURRENT_SCORE));
        }
    }

    @Override public void onResume() {
        super.onResume();
        this.playerComparisonPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.playerComparisonPresenter.pause();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.playerComparisonPresenter.destroy();
    }

    @Override public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(INSTANCE_STATE_PARAM_PLAYER_1 , playerComparisonPresenter.getPlayer1());
        outState.putParcelable(INSTANCE_STATE_PARAM_PLAYER_2 , playerComparisonPresenter.getPlayer2());
        outState.putInt(INSTANCE_STATE_PARAM_CURRENT_SCORE, Integer.valueOf(currentScore.getText().toString()));
    }

    @Override
    public void showLoading(){}

    @Override
    public void hideLoading(){}

    @Override
    public void showRetry(){}

    @Override
    public void hideRetry(){}

    @Override
    public void renderPlayers(PlayerModel player1, PlayerModel player2) {
        photoPlayer1.setImageUrl(player1.getImageUrl());
        namePlayer1.setText(player1.getName());
        fppg.setVisibility(View.VISIBLE);
        fppgPlayer1.setText(String.valueOf(player1.getFppg()));
        photoPlayer2.setImageUrl(player2.getImageUrl());
        namePlayer2.setText(player2.getName());
    }

    @Override
    public void updateScore(int score){
        if(score == 0){
            gameOver.setVisibility(View.GONE);
            playAgain.setVisibility(View.GONE);
            higherButton.setVisibility(View.VISIBLE);
            lowerButton.setVisibility(View.VISIBLE);
            higherOrLower.setVisibility(View.VISIBLE);
        }
        if(score < 10){
            currentScore.setText(Integer.toString(score));
            if(!savedInstanceReload){
                playerComparisonPresenter.switchPlayers();
            } else {
                savedInstanceReload = false;
            }
        } else {
            currentScore.setText(Integer.toString(score));
            higherButton.setVisibility(View.GONE);
            lowerButton.setVisibility(View.GONE);
            higherOrLower.setVisibility(View.GONE);
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
