package com.example.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

// Retrofit
// Provide interfaces to handle API requests w/ different URLs
// and different HTTP request methods
// Use Call object to execute actual GET request
public interface JsonPlaceHolderApi {

    // URL .../posts - relative URL
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> params);

    // Path variables - get specific posts' comments
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    // Query parameters
    @GET("posts")
    Call<List<Post>> getUserPosts(@Query("userId") int userId);
}
