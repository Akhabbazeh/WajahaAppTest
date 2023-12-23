package com.example.WajahaAppTest.feature.product_feature;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.WajahaAppTest.R;
import com.example.WajahaAppTest.model.Products;

import java.util.List;

public class RecycleViewAdapterProducts extends  RecyclerView.Adapter<RecycleViewAdapterProducts.ProductsViewHolder>{

    private List<Products> products_datA;
    private Context context;

    public List<Products> getProducts_datA() {
        return products_datA;
    }

    public RecycleViewAdapterProducts(Context context,List<Products> products_datA) {

        this.products_datA = products_datA;
        this.context = context;
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
        holder.text_price.setText(products_datA.get(position).getPrice()+" sp");
        holder.dateOfPublication.setText(products_datA.get(position).getDateOfPublication());
        Glide.with(context)
                .load(products_datA.get(position).getPrimary_Image().getLinkPrimaryImage())
                .into(holder.primaryImage);

        holder.constraintLayoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AboutProductActivity.class);
                intent.putExtra("product_num",position);
                view.getContext().startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return products_datA.size();
    }



    class ProductsViewHolder extends RecyclerView.ViewHolder{

        TextView name_product;
        TextView text_price;
        TextView dateOfPublication;
        ImageView primaryImage;
        ConstraintLayout constraintLayoutProduct;


        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product=itemView.findViewById(R.id.name_product);
            text_price=itemView.findViewById(R.id.text_price);
            dateOfPublication=itemView.findViewById(R.id.dateOfPublication);
            primaryImage=itemView.findViewById(R.id.primaryImage);
            constraintLayoutProduct=itemView.findViewById(R.id.ConstraintLayoutProduct);





        }
    }
}
