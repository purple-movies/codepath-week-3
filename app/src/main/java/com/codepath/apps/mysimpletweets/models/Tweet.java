package com.codepath.apps.mysimpletweets.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tweet {
    
    private String body;
    private long uid; // UID for the tweet.
    private User user;
    private String createdAt;

    public String getBody() {
        return body;
    }

    public long getUid() {
        return uid;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public static Tweet fromJSON(JSONObject json) {
        Tweet tweet = new Tweet();

        try {
            tweet.body = json.getString("text");
            tweet.uid = json.getLong("id");
            tweet.createdAt = json.getString("created_at");

            tweet.user = User.fromJSON(json.getJSONObject("user"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return tweet;
    }

    public static ArrayList<Tweet> fromJsonArray(JSONArray array) {
        
        ArrayList<Tweet> tweets = new ArrayList<>();
        
        for (int i=0; i < array.length(); i++) {

            try {
                JSONObject tweetJson = array.getJSONObject(i);
                Tweet tweet = Tweet.fromJSON(tweetJson);
                if (tweet != null) {
                    tweets.add(tweet);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }
        }
        
        return tweets;
    }
}
