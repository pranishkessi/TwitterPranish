package com.si.twitterpranish.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samyam.twitter.R;
import com.samyam.twitter.model.Tweets;
import com.samyam.twitter.strictMode.StrictModeClass;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.TweetList> {
    Context context;
    List<Tweets> dataSetList;
    public static final String base_url = "http://10.0.2.2:3000/";
    String imagePath = base_url + "uploads/tweets/";
    String user_imagePath = base_url + "uploads/users/";

    public TweetAdapter(Context context, List<Tweets> dataSetList) {
        this.context = context;
        this.dataSetList = dataSetList;
    }

    @NonNull
    @Override
    public TweetAdapter.TweetList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tweet_recycle, parent, false);
        return new TweetAdapter.TweetList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TweetAdapter.TweetList holder, int position) {
        Tweets tweets = dataSetList.get(position);
        holder.tweets.setText(tweets.getTweet());
        holder.name.setText(tweets.getName());
        holder.username.setText("@" + tweets.getUsername());

        StrictModeClass.StrictMode();
        String imgPath = imagePath + tweets.getImage();
        try {
            URL url = new URL(imgPath);
            holder.image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String userpath = user_imagePath + tweets.getUser_image();
        try {
            URL url = new URL(userpath);
            holder.user_image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataSetList.size();
    }

    public class TweetList extends RecyclerView.ViewHolder {
        ImageView image, user_image;
        TextView tweets, name, username;

        public TweetList(@NonNull View itemView) {
            super(itemView);
            tweets = itemView.findViewById(R.id.tweet);
            name = itemView.findViewById(R.id.tweet_name);
            username = itemView.findViewById(R.id.tweet_username);
            image = itemView.findViewById(R.id.tweet_image);
            user_image = itemView.findViewById(R.id.user_image);
        }
    }
}
