package com.example.WajahaAppTest.http;


import com.example.WajahaAppTest.feature.product_feature.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainWebInterface {
    @GET("api/products")
    Call<List<Products>> getProducts();


}
