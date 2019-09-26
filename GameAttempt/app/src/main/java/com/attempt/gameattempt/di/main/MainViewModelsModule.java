package com.attempt.gameattempt.di.main;


import androidx.lifecycle.ViewModel;

import com.attempt.gameattempt.di.ViewModelKey;
import com.attempt.gameattempt.ui.main.posts.PostsViewModel;
import com.attempt.gameattempt.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}