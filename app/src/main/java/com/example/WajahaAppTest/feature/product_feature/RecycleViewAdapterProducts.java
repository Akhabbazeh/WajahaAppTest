package com.example.WajahaAppTest.feature.product_feature;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WajahaAppTest.R;

import java.util.List;

public class RecycleViewAdapterProducts extends  RecyclerView.Adapter<RecycleViewAdapterProducts.ProductsViewHolder>{

    private List<Products> products_datA;

    public List<Products> getProducts_datA() {
        return products_datA;
    }

    public void setProducts_datA(List<Products> products_datA) {
        this.products_datA = products_datA;
    }

    @NonNull
    @Override
    public RecycleViewAdapterProducts.ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_card_view,null,false);
        ProductsViewHolder viewHolder = new ProductsViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapterProducts.ProductsViewHolder holder, int position) {
        holder.name_product.setText(products_datA.get(position).getName());
        holder.text_price.setText(products_datA.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return products_datA.size();
    }



    class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView name_product;
        TextView text_price;


        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product=itemView.findViewById(R.id.name_product);
            text_price=itemView.findViewById(R.id.text_price);



        }
    }
}
