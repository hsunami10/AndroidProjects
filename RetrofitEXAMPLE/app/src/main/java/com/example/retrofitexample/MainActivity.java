package com.example.retrofitexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private int postId = 1;
    private int userId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.text_view_result);

        // Initialize retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        // Create a JsonPlaceHolderApi object from interface - retrofit handles this
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

//        getAllPosts();
//        getComments();
//        getUserPosts();
//        createPost();
//        updatePost();
        deletePost();
    }

    private void deletePost() {
        Call<Void> call = jsonPlaceHolderApi.deletePost(5);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                textViewResult.setText("Code: " + response.code());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void updatePost() {
        Post post = new Post(12, null, "New Text");
//        Call<Post> call = jsonPlaceHolderApi.putPost(5, post);
        Call<Post> call = jsonPlaceHolderApi.patchPost(5, post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResp = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResp.getId() + "\n";
                content += "User ID: " + postResp.getUserId() + "\n";
                content += "Title: " + postResp.getTitle() + "\n";
                content += "Text: " + postResp.getText() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void createPost() {
        // Request body
//        Post post = new Post(23, "New Title", "New Text!!!");
//        Call<Post> call = jsonPlaceHolderApi.createPost(23, "New Title", "New Text");

        // Form urlencoded
        Call<Post> call = jsonPlaceHolderApi.createPost(23, "New Title", "New Text");
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Post postResp = response.body();
                String content = "";
                content += "Code: " + response.code() + "\n";
                content += "ID: " + postResp.getId() + "\n";
                content += "User ID: " + postResp.getUserId() + "\n";
                content += "Title: " + postResp.getTitle() + "\n";
                content += "Text: " + postResp.getText() + "\n\n";

                textViewResult.setText(content);
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getUserPosts() {
        Call<List<Post>> call = jsonPlaceHolderApi.getUserPosts(userId);
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getComments() {
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(postId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                // Successful response!
                List<Comment> comments = response.body();
                for (Comment comment: comments) {
                    String content = String.format("Post ID: %d\nComment ID: %d\nAuthor: %s\nEmail: %s\nBody: %s\n", comment.getPostId(), comment.getId(), comment.getAuthor(), comment.getEmail(), comment.getText());
                    textViewResult.append(content + "\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getAllPosts() {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "1");
        params.put("_sort", "id");
        params.put("_order", "desc");

        // Execute request
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(params);


        // Queue up the network request to run it - on another thread (concurrently)
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                // This doesn't mean the request was successful - check HTTP response code
                if (!response.isSuccessful()) { // If not successful HTTP response
                    textViewResult.setText("Code: " + response.code()); // Data is null
                    return;
                }

                List<Post> posts = response.body();
                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID: " + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text: " + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
