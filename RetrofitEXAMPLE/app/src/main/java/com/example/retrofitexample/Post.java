package com.example.retrofitexample;

import com.squareup.moshi.Json;

public class Post {
    // These must be exactly the same in JSON
    private int userId;
    private Integer id; // allow nulls - Integer class type
    private String title;

    // Can have custom field names - change body to text
    @Json(name = "body")
    private String text;

    public Post(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

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
