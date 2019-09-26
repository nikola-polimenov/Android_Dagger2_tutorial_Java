package com.attempt.gameattempt.di.main;


import com.attempt.gameattempt.network.main.MainApi;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {




    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }

}
