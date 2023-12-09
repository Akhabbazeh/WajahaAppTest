package com.example.WajahaAppTest.feature.product_feature;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.WajahaAppTest.http.RequestBuilder;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends ViewModel {
    public MutableLiveData <List<Products>> mutableLiveDataProducts= new MutableLiveData<>();

    public void getProductsModel(){
        RequestBuilder.getINSTANCE().getProducts().enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                mutableLiveDataProducts.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {


            }
        });


    }
}
