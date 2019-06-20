package com.example.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// Retrofit
// Provide interfaces to handle API requests w/ different URLs
// and different HTTP request methods
// Use Call object to execute actual GET request
public interface JsonPlaceHolderApi {

    // URL .../posts - relative URL
    @GET("posts")
    Call<List<Post>> getPosts();
}
