package com.example.WajahaAppTest.http;
import android.content.Context;

import com.example.WajahaAppTest.feature.product_feature.Products;
import com.example.WajahaAppTest.ui.MainActivity;
import com.example.WajahaAppTest.ui.MyApplication;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RequestBuilder {
    private static final String Base_URL="https://wajaha-api.beetronix.net/";
    public static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    private MainWebInterface mainWebInterface;
    private static RequestBuilder INSTANCE;

    int cacheSize = 10 * 1024 * 1024; // 10 MB


    public  RequestBuilder() {

        Retrofit retrofitConnection = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mainWebInterface= retrofitConnection.create(MainWebInterface.class);

    }
   Cache cache = new Cache(MyApplication.getInstance().getCacheDir(), cacheSize);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .cache(cache)
            .build();

    public static RequestBuilder getINSTANCE(){

        if (null==INSTANCE){
            INSTANCE=new RequestBuilder();
        }
    return INSTANCE;
    }

    public Call<List<Products>> getProducts(){
        return mainWebInterface.getProducts(true);
    }





}



