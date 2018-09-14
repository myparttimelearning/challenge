package com.rumodigi.fanduelmobilechallenge.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;


import com.rumodigi.fanduelmobilechallenge.domain.usecases.GetPlayerList;
import com.rumodigi.fanduelmobilechallenge.presentation.R;
import com.rumodigi.fanduelmobilechallenge.presentation.presenter.PlayerListPresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.loadDataButton)
    Button loadDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loadDataButton)
    void navigateToUserList() {
        this.navigator.navigateToUserList(this);
    }
}
