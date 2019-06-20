package com.example.retrofitexample;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

// Retrofit
// Provide interfaces to handle API requests w/ different URLs
// and different HTTP request methods
// Use Call object to execute actual GET request
public interface JsonPlaceHolderApi {

    // Get examples

    // URL .../posts - relative URL
    @GET("posts")
    Call<List<Post>> getPosts(@QueryMap Map<String, String> params);

    // Path variables - get specific posts' comments
    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);

    // Query parameters
    @GET("posts")
    Call<List<Post>> getUserPosts(@Query("userId") int userId);

    // Post examples

    // ======================= 2 ways to send over request body) =======================

    // Post example (directly with request body)
    // More common post method (compared to form urlencoded)
    @POST("posts")
    Call<Post> createPost(@Body Post post);

    // Post example (form urlencoded)
    // Different way of encoding data (encoded same way as URL)
    // Usually used in HTML forms
    @FormUrlEncoded
    @POST
    Call<Post> createPost(
            @Field("userId") int userId,
            @Field("title") String title,
            @Field("body") String text
    );
    @FormUrlEncoded
    @POST
    Call<Post> createPost(
            @FieldMap Map<String, String> fields,
            @Field("extra_field") String value
    );

    // Put example - complete replacement of data
    @PUT("posts/{id}")
    Call<Post> putPost(@Path("id") int id, @Body Post post);

    // Patch example - only change certain fields that exist (if null, don't update)
    @PATCH("posts/{id}")
    Call<Post> patchPost(@Path("id") int id, @Body Post post);

    // Delete example
    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);

}
