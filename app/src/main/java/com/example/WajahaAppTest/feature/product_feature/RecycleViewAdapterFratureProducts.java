package com.example.WajahaAppTest.feature.product_feature;

import android.content.Context;
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

import java.util.List;

public class RecycleViewAdapterFratureProducts extends  RecyclerView.Adapter<RecycleViewAdapterFratureProducts.FeatureViewHolder>{

    private List<Products> products_datA;
    private Context context;
    private int item_re_num=0;

    public List<Products> getProducts_datA() {
        return products_datA;
    }

    public RecycleViewAdapterFratureProducts(Context context,List<Products> products_datA) {

        this.products_datA = products_datA;
        this.context = context;
    }

    public void setFeatureProducts_datA(List<Products> products_datA) {
        this.products_datA = products_datA;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecycleViewAdapterFratureProducts.FeatureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_card_view,null,false);
        FeatureViewHolder viewHolder = new FeatureViewHolder(v);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FeatureViewHolder holder, int position) {

        if(products_datA.get(position).getIsFeatured()==true){
        item_re_num=item_re_num+1;
        holder.name_product.setText(products_datA.get(position).getName());
        holder.text_price.setText(products_datA.get(position).getPrice()+" sp");
        holder.dateOfPublication.setText(products_datA.get(position).getDateOfPublication());
        Glide.with(context)
                .load(products_datA.get(position).getPrimary_Image().getLinkPrimaryImage())
                .into(holder.primaryImage);

        holder.constraintLayoutProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        }
    }


    @Override
    public int getItemCount() {
        return item_re_num;
    }



    class FeatureViewHolder extends RecyclerView.ViewHolder{

        TextView name_product;
        TextView text_price;
        TextView dateOfPublication;
        ImageView primaryImage;
        ConstraintLayout constraintLayoutProduct;


        public FeatureViewHolder(@NonNull View itemView) {
            super(itemView);
            name_product=itemView.findViewById(R.id.name_product);
            text_price=itemView.findViewById(R.id.text_price);
            dateOfPublication=itemView.findViewById(R.id.dateOfPublication);
            primaryImage=itemView.findViewById(R.id.primaryImage);
            constraintLayoutProduct=itemView.findViewById(R.id.ConstraintLayoutProduct);





        }
    }
}
