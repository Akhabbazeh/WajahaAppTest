package com.example.WajahaAppTest.ui;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.WajahaAppTest.R;
import com.example.WajahaAppTest.feature.product_feature.Products;
import com.example.WajahaAppTest.feature.product_feature.ProductsViewModel;
import com.example.WajahaAppTest.feature.product_feature.RecycleViewAdapterProducts;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProductsViewModel productsViewModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        productsViewModel.getProductsModel();
        RecyclerView recyclerViewProduct= findViewById(R.id.rvProduct);
        TextView textView = findViewById(R.id.textView1);

        RecycleViewAdapterProducts adapterProducts = new RecycleViewAdapterProducts();
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(this));
       // recyclerViewProduct.setAdapter(adapterProducts);


        productsViewModel.mutableLiveDataProducts.observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {
         //       adapterProducts.setProducts_datA(products);
                Toast.makeText(getApplicationContext(), "The connection was successful", Toast.LENGTH_LONG).show();
            }
        });



    }
}