package com.example.WajahaAppTest.feature.product_feature;

import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.WajahaAppTest.data.Status;
import com.example.WajahaAppTest.data.product_data.ProductResponse;
import com.example.WajahaAppTest.data.product_data.ProductViewModel;

public class ProductManager implements ProductMangerInterface{

    private ProductUiInterface productUiInterface;

    private Fragment modelStoreOwner;

    public ProductManager(ProductUiInterface productUiInterface, Fragment modelStoreOwner)
    {
        this.productUiInterface = productUiInterface;
        this.modelStoreOwner = modelStoreOwner;
        //00
    }

    @Override
    public void requestProduct(boolean order) {
        productUiInterface.showProductLoading();

        ProductViewModel productViewModel = new ViewModelProvider(modelStoreOwner).get(ProductViewModel.class);

        productViewModel.getData(order).observe(modelStoreOwner, new Observer<ProductResponse>() {
            @Override
            public void onChanged(ProductResponse productResponse) {

                Log.e("TAG", "onChanged: " + productResponse.getStatus());
                if (productResponse.getStatus() == Status.Loading) {
                    productUiInterface.showProductLoading();
                }
                if (productResponse.getStatus() == Status.LoadedFromDB) {
                    productUiInterface.showProduct(productResponse.getProduct());
                    productUiInterface.hideProductLoading();
                } else if (productResponse.getStatus() == Status.NetworkSuccess) {
                    productUiInterface.showProduct(productResponse.getProduct());
                    productUiInterface.hideProductLoading();
                } else if (productResponse.getStatus() == Status.NetworkError) {
                    productUiInterface.showProductError();
                    productUiInterface.hideProductLoading();
                }
            }
        });
    }

    @Override
    public void refreshProduct(boolean order)
    {
        ProductViewModel ProductViewModel = new ViewModelProvider(modelStoreOwner).get(ProductViewModel.class);
        ProductViewModel.refreshData(order);
    }
}
