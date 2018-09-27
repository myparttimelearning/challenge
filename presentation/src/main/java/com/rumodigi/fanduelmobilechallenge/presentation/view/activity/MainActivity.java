package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;


import com.rumodigi.fanduelmobilechallenge.presentation.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.startGame)
    ConstraintLayout startGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.startGame)
    void navigateToPlayerComparison() {
        if(this.checkInternetConnection.isThereInternetConnection(getApplicationContext())){
            this.navigator.navigateToPlayerComparison(this);
        } else {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();
        }
    }
}
