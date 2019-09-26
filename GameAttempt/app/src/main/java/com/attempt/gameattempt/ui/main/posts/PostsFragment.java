package com.attempt.gameattempt.ui.main.posts;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.attempt.gameattempt.R;
import com.attempt.gameattempt.models.Post;
import com.attempt.gameattempt.ui.main.Resource;
import com.attempt.gameattempt.util.VerticalSpacingItemDecoration;
import com.attempt.gameattempt.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostsFragment extends DaggerFragment {

    private static final String TAG = "PostsFragment";
    private AppCompatTextView textView_test;

    private PostsViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView_test = view.findViewById(R.id.text_view_test);


        viewModel = ViewModelProviders.of(this, providerFactory).get(PostsViewModel.class);

        subscribeObservers();

    }

    private void subscribeObservers() {
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Post>>>() {
            @Override
            public void onChanged(Resource<List<Post>> listResource) {
                if (listResource != null) {
                    switch (listResource.status) {
                        case LOADING:{
                            Log.d(TAG, "onChanged: Loading... ");
                            break;
                        }
                        case SUCCESS:{
                            Log.d(TAG, "onChanged: Successfully retrieved posts! ");
                            textView_test.setText((listResource.data).toString());
                            break;
                        }
                        case ERROR:{
                            Log.e(TAG, "onChanged: ERROR" + listResource.message );
                            break;
                        }
                    }
                }
            }
        });
    }

}
