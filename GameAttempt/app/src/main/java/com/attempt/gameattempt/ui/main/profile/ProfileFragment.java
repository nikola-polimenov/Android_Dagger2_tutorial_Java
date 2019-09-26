package com.attempt.gameattempt.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.attempt.gameattempt.R;
import com.attempt.gameattempt.models.User;
import com.attempt.gameattempt.ui.auth.AuthResource;
import com.attempt.gameattempt.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private static final String TAG = "ProfileFragment";

    private ProfileViewModel viewModel;
    private TextView email, website, username;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: Profile fragment was created...");
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);
        username = view.findViewById(R.id.username);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ProfileViewModel.class);

        subscribeObservers();
    }

    private void subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if(userAuthResource != null) {
                    switch (userAuthResource.status){
                        case AUTHENTICATED:{
                            setUserDetails(userAuthResource.data);
                            break;
                        }case ERROR: {
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message) {
        email.setText(message);
        website.setText("error");
        username.setText("error");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
        username.setText(data.getUsername());
    }
}
