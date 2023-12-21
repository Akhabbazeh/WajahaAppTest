package com.example.WajahaAppTest.feature.product_feature;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.WajahaAppTest.R;
import com.example.WajahaAppTest.model.Products;

import java.util.ArrayList;
import java.util.List;

public class AboutProductActivity extends AppCompatActivity {

 private    TextView some_notes;
  //private   ProductsViewModel productsViewModel ;
  private   TextView descripition_text;
  private   ViewPager mViewPager;
    private List<Products> About_productsData;
    private int product_num;
    private ArrayList<String> images = new ArrayList<>();

    ViewPageAdapterCartShopping mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_about_product);
        descripition_text=findViewById(R.id.descripition_text);
        some_notes=findViewById(R.id.some_notes);
        mViewPager =findViewById(R.id.ViewPager_cart_shopping);

        Intent intent = getIntent();
        product_num = intent.getIntExtra("product_num", 0);

//        productsViewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
//        productsViewModel.getProductsModel();
//        productsViewModel.mutableLiveDataProducts.observe(this, new Observer<List<Products>>() {
//            @Override
//            public void onChanged(List<Products> products) {
//                descripition_text.setText(products.get(product_num).getDescription());
//
//                for (int i=0;i<products.get(product_num).getImages().size();i++){
//                    images.add(products.get(product_num).getImages().get(i).getUrl());
//                }
//                mViewPagerAdapter = new ViewPageAdapterCartShopping(AboutProductActivity.this, images);
//                mViewPager.setAdapter(mViewPagerAdapter);
//            }
//        });



    }
}