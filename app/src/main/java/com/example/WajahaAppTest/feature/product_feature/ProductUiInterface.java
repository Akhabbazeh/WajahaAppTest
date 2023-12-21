package com.example.WajahaAppTest.feature.product_feature;

import com.example.WajahaAppTest.model.Products;

import java.util.List;

public interface ProductUiInterface {
    void showProduct(List<Products> products);
    void showProductLoading();
    void hideProductLoading();
    void showProductError();
}
