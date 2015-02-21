package com.codepath.apps.mysimpletweets;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.mysimpletweets.models.Tweet;
import com.codepath.apps.mysimpletweets.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TweetsArrayAdapter extends ArrayAdapter<Tweet> {

    public TweetsArrayAdapter(Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        Tweet tweet = getItem(position);
        
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);
        }

        ImageView ivProfile = (ImageView) convertView.findViewById(R.id.ivProfileImage);
        TextView tvName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvTimeStamp = (TextView) convertView.findViewById(R.id.tvTimeStamp);
        TextView tvBody = (TextView) convertView.findViewById(R.id.tvBody);

        final User user = tweet.getUser();
        tvName.setText(user.getScreenName()+ " : " + user.getName());
        tvTimeStamp.setText(DateUtils.getRelativeTimeSpanString(tweet.getCreatedAtDate().getTime()));
        tvBody.setText(tweet.getBody());

        ivProfile.setImageResource(0);
        Picasso.with(getContext()).load(user.getProfileImageUrl()).into(ivProfile);
        return convertView;
    }
}
