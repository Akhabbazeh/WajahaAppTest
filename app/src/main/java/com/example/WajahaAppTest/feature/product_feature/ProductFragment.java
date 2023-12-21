package com.example.WajahaAppTest.feature.product_feature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WajahaAppTest.R;
import com.example.WajahaAppTest.model.Products;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment implements ProductUiInterface{


    private View view;
    RecyclerView recyclerViewProduct;
    private boolean order;
    private RecycleViewAdapterProducts recycleViewAdapterProducts;
    private List<Products> products;
    private ProductMangerInterface productMangerInterface;

    public ProductFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_product, container, false);

        recyclerViewProduct= view.findViewById(R.id.rvProduct);

        initProductRecycler();

        productMangerInterface = new ProductManager(this, this) ;

            Bundle bundle = getArguments();

                    if(bundle != null)
            {
                order = bundle.getBoolean("true");
            }

            getData(order);

        return view;
        }


    private void getData(boolean order)
    {
        productMangerInterface.requestProduct(order);

    }

    private void refreshData(boolean order)
    {
        productMangerInterface.refreshProduct(order);
    }

        private void initProductRecycler()
    {
        products = new ArrayList<>();

        recycleViewAdapterProducts = new RecycleViewAdapterProducts(getContext(),products);

        recyclerViewProduct.setAdapter(recycleViewAdapterProducts);
        recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewProduct.setHasFixedSize(true);
    }
    @Override
    public void showProduct(List<Products> products) {
        this.products.clear();
       // this.products.addAll(products);
        recycleViewAdapterProducts.notifyDataSetChanged();

    }

    @Override
    public void showProductLoading() {

    }

    @Override
    public void hideProductLoading() {
    }

    @Override
    public void showProductError() {

    }
}
