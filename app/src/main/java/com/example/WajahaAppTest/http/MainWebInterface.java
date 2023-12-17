package com.example.WajahaAppTest.http;


import com.example.WajahaAppTest.feature.product_feature.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface MainWebInterface {
    @Headers("Accept-Language: en")
    @GET("api/products")
    Call<List<Products>> getProducts(@Query("order") boolean state);



}
