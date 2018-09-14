package com.rumodigi.fanduelmobilechallenge.presentation.di.components;

import com.rumodigi.fanduelmobilechallenge.presentation.di.PerActivity;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.ActivityModule;
import com.rumodigi.fanduelmobilechallenge.presentation.di.modules.PlayerModule;
import com.rumodigi.fanduelmobilechallenge.presentation.view.fragments.PlayerListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PlayerModule.class})
public interface PlayerComponent {
    void inject(PlayerListFragment playerListFragment);
}
