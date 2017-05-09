package com.example.android.bakingappnanodegree.data.backend;

import android.util.Log;

import com.example.android.bakingappnanodegree.model.Recipe;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by micky on 07-May-17.
 */

public class RecipeSerivce {

    private static String BASE_URL =  "https://d17h27t6h515a5.cloudfront.net/topher/2017/March/58d1537b_baking/";

    public interface  RecipesAPI{
        @GET("baking.json")
        Call<List<Recipe>> getRecipies();
    }

    public RecipesAPI getRecipesAPI(){



        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        return retrofit.create(RecipesAPI.class);
    }


    private static OkHttpClient.Builder clientBuilder;
    static {
        clientBuilder = new OkHttpClient
                .Builder()
                .connectTimeout(80, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
//                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        Response response = chain.proceed(request);
                        okhttp3.Headers headers = request.headers();

                        Log.d("Request Headers", (headers != null) ? headers.toString() : "Null headers");
                        Log.d("Response Body",response.body().string());

                        return chain.proceed(request);
                    }
                });
    }



}
