package com.example.retrofitexample;

import com.squareup.moshi.Json;

public class Comment {
    private int postId;
    private int id;
    private String email;

    @Json(name = "name")
    private String author;

    @Json(name = "body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }
}
