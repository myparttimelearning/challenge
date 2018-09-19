package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.di.ContainsComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.DaggerPlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.PlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.navigation.Navigator;
import com.rumodigi.fanduelmobilechallenge.presentation.view.fragments.PlayerComparisonFragment;

import javax.inject.Inject;

public class PlayerComparisonActivity extends BaseActivity implements ContainsComponent<PlayerComponent>{

    private static final String INSTANCE_STATE_PLAYER_COMPONENT = "com.rumodigi.STATE_PLAYER_COMPONENT";

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PlayerComparisonActivity.class);
    }

    private PlayerComponent playerComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this.initializeInjector() Moved above call to super as component object is null
        // if kill activities is enabled in dev options.
        //TODO There must be a better way to do this!
        this.initializeInjector();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new PlayerComparisonFragment());
        }
    }

    private void initializeInjector() {
        this.playerComponent = DaggerPlayerComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    public PlayerComponent getComponent() {
        return playerComponent;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}

