package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;

import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.di.ContainsComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.DaggerPlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.di.components.PlayerComponent;
import com.rumodigi.fanduelmobilechallenge.presentation.view.fragments.PlayerComparisonFragment;

import butterknife.Bind;
import butterknife.OnClick;

public class PlayerListActivity extends BaseActivity implements ContainsComponent<PlayerComponent>{

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, PlayerListActivity.class);
    }

    private PlayerComponent playerComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setContentView(R.layout.activity_player_list);

        this.initializeInjector();
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
}

