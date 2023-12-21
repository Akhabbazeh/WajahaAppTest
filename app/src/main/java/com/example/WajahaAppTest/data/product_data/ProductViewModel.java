package com.example.WajahaAppTest.data.product_data;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.WajahaAppTest.model.Products;



public class ProductViewModel extends AndroidViewModel
{
    private ProductRepository productRepository;
    private LiveData<ProductResponse> productResponse;

    public ProductViewModel(Application application)
    {
        super(application);
        productRepository = new ProductRepository(application);
    }

    public LiveData<ProductResponse> getData(boolean order)
    {
        productResponse = productRepository.getProduct(order);
        return productResponse;
    }

    public void refreshData(boolean order)
    {
        productRepository.refreshComments(order);
    }
}
