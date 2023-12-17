package com.example.WajahaAppTest.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;

import com.example.WajahaAppTest.R;
import com.example.WajahaAppTest.feature.product_feature.Products;
import com.example.WajahaAppTest.feature.product_feature.ProductsViewModel;
import com.example.WajahaAppTest.feature.product_feature.RecycleViewAdapterProducts;
import com.example.WajahaAppTest.http.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductsViewModel productsViewModel ;
    List<Products> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.getProductsModel();
        RecyclerView recyclerViewProduct= findViewById(R.id.rvProduct);
        TextView textView = findViewById(R.id.textView1);

        products = new ArrayList<>();
        RecycleViewAdapterProducts adapterProducts = new RecycleViewAdapterProducts(this,products);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewProduct.setAdapter(adapterProducts);


        productsViewModel.mutableLiveDataProducts.observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
                adapterProducts.setProducts_datA(products);
            }
        });


    }
}