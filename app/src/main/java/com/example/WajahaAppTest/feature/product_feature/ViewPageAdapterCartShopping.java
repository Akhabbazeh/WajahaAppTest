package com.example.WajahaAppTest.feature.product_feature;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.WajahaAppTest.R;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class ViewPageAdapterCartShopping extends PagerAdapter {

    Context context;

    ArrayList<String> images;

    LayoutInflater mLayoutInflater;


    // Viewpager Constructor
    public ViewPageAdapterCartShopping(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.view_page_holder_cart_shopping, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_view_page_holder_cart_shopping);

       // imageView.setImageResource(images[position]);

        Glide.with(context)
                .load(images.get(position))
                .into(imageView);

        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((LinearLayout) object);
    }
}
