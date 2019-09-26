package com.attempt.gameattempt.di;


import com.attempt.gameattempt.di.auth.AuthModule;
import com.attempt.gameattempt.di.auth.AuthScope;
import com.attempt.gameattempt.di.auth.AuthViewModelsModule;
import com.attempt.gameattempt.di.main.MainFragmentBuildersModule;
import com.attempt.gameattempt.di.main.MainModule;
import com.attempt.gameattempt.di.main.MainScope;
import com.attempt.gameattempt.di.main.MainViewModelsModule;
import com.attempt.gameattempt.ui.auth.AuthActivity;
import com.attempt.gameattempt.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @AuthScope
    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(
            modules = {
                    MainFragmentBuildersModule.class,
                    MainViewModelsModule.class,
                    MainModule.class
            }
    )
    abstract MainActivity contributeMainActivity();



}
