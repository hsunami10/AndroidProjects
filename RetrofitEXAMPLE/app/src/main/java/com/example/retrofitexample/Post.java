package com.example.retrofitexample;

import com.squareup.moshi.Json;

public class Post {
    // These must be exactly the same in JSON
    private int userId;
    private int id;
    private String title;

    // Can have custom field names - change body to text
    @Json(name = "body")
    private String text;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
