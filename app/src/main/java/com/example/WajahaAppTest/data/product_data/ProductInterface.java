package com.example.WajahaAppTest.data.product_data;
import com.example.WajahaAppTest.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ProductInterface {
    @Headers("Accept-Language: en")
    @GET("api/products")
    Call<List<Products>> getProducts(@Query("order") boolean state);


}
