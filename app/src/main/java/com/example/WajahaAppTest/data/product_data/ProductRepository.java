package com.example.WajahaAppTest.data.product_data;

import android.app.Application;
import android.util.Log;
import com.example.WajahaAppTest.data.RetrofitClient;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.WajahaAppTest.data.Status;
import com.example.WajahaAppTest.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    private ProductResponse productResponse;

    private static final String TAG = "RetrofitClient";

    MutableLiveData<ProductResponse> productResponseMutableLiveData;

    public ProductRepository(Application application)
    {
        productResponseMutableLiveData = new MutableLiveData<>();

        productResponse = new ProductResponse(null,null, Status.Loading);

    }

    public LiveData<ProductResponse> getProduct(boolean order)
    {
        ProductInterface productInterface = RetrofitClient.getRetrofit(true,false,false).create(ProductInterface.class);

        productResponseMutableLiveData.postValue(productResponse);

        Call<List<Products>> call = productInterface.getProducts(order);

        call.enqueue(new Callback<List<Products>>()
        {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response)
            {
                if(response.isSuccessful())
                {
                    productResponse.setProduct(response.body());
                    productResponse.setStatus(Status.NetworkSuccess);
                    productResponseMutableLiveData.postValue(productResponse);
                }
                else
                {
                    productResponse.setStatus(Status.NetworkError);
                }

                if (response.raw().networkResponse() != null)
                {
                    Log.e(TAG, "response came from server");
                }
                else if(response.raw().cacheResponse() != null)
                {
                    Log.e(TAG, "response came from cache");
                }
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t)
            {
                productResponse.setError(t);
                productResponse.setStatus(Status.NetworkError);
                productResponseMutableLiveData.postValue(productResponse);
            }
        });

        return productResponseMutableLiveData;
    }

    public void refreshComments(boolean order)
    {

        ProductInterface productInterface = RetrofitClient.getRetrofit(true,true,false).create(ProductInterface.class);

        Call<List<Products>> call = productInterface.getProducts(order);

        call.enqueue(new Callback<List<Products>>()
        {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response)
            {
                if(response.isSuccessful())
                {
                    productResponse.setProduct(response.body());
                    productResponse.setStatus(Status.NetworkSuccess);
                    productResponseMutableLiveData.postValue(productResponse);
                }
                else
                {
                    productResponse.setStatus(Status.NetworkError);
                }

                if (response.raw().networkResponse() != null)
                {
                    Log.e(TAG, "response came from server");
                }
                else if(response.raw().cacheResponse() != null)
                {
                    Log.e(TAG, "response came from cache");
                }

                RetrofitClient.forceNetworking = false;

            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t)
            {
                productResponse.setError(t);
                productResponse.setStatus(Status.NetworkError);
                productResponseMutableLiveData.postValue(productResponse);

                RetrofitClient.forceNetworking = false;
            }
        });
    }



}
