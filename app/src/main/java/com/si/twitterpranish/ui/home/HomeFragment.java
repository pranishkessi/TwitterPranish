package com.si.twitterpranish.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.si.twitterpranish.ImageActivity;
import com.si.twitterpranish.LoginActivity;
import com.si.twitterpranish.R;
import com.si.twitterpranish.adapter.TweetAdapter;
import com.si.twitterpranish.apis.APIClass;
import com.si.twitterpranish.model.Tweets;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;

    LoginActivity la = new LoginActivity();
    ImageActivity img = new ImageActivity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.recycle);
        loadCurrentUser();
        return root;
    }

    private void loadCurrentUser() {
        String token;
        if (la.Token.equals("Bearer ")) {
            token = img.token;
            //Toast.makeText(getContext(), "token " +token, Toast.LENGTH_SHORT).show();

        } else {
            token = la.Token;
            // Toast.makeText(getContext(), "token " +token, Toast.LENGTH_SHORT).show();
        }

        APIClass usersAPI = new APIClass();

        Call<List<Tweets>> userCall = usersAPI.calls().GetTweet(token);
        userCall.enqueue( new Callback<List<Tweets>>() {
            @Override
            public void onResponse(Call<List<Tweets>> call, Response<List<Tweets>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( getContext(), "Code " + response.code(), Toast.LENGTH_SHORT ).show();
                    return;
                }
                List<Tweets> tweetMS = response.body();

                TweetAdapter tweetAdapter = new TweetAdapter( getContext(), tweetMS );
                recyclerView.setAdapter( tweetAdapter );
                recyclerView.setLayoutManager( new LinearLayoutManager( getContext() ) );
            }

            @Override
            public void onFailure(Call<List<Tweets>> call, Throwable t) {
                Toast.makeText( getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );


    }

}