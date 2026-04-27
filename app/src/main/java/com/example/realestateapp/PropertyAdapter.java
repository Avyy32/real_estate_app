package com.example.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder> {

    private final List<Property> properties;
    private final Context context;

    public PropertyAdapter(Context context, List<Property> properties) {
        this.context = context;
        this.properties = properties;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_property_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Property property = properties.get(position);
        
        holder.tvType.setText(property.getPropertyType() != null ? property.getPropertyType().toUpperCase() : "PROPERTY");
        holder.tvSubTitle.setText(property.getBhk() + " House in " + property.getLocation());
        holder.tvPrice.setText(property.getPrice());
        holder.tvArea.setText(property.getSqft() != null ? property.getSqft() : "N/A");
        holder.tvDealerName.setText(property.getDealerName() != null ? property.getDealerName() : "Unknown Dealer");
        holder.tvListingType.setText(property.getListingType() != null ? property.getListingType() : "Resale");

        ImagePagerAdapter imageAdapter = new ImagePagerAdapter(context, property.getAdditionalImages());
        holder.viewPager.setAdapter(imageAdapter);
        
        new TabLayoutMediator(holder.tabLayout, holder.viewPager, (tab, pos) -> {}).attach();

        View.OnClickListener contactListener = v -> {
            Intent intent = new Intent(context, ShareDetailsActivity.class);
            if (v.getId() == R.id.btnWhatsapp) {
                intent.putExtra("mode", "whatsapp");
            } else {
                intent.putExtra("mode", "call");
            }
            context.startActivity(intent);
        };

        holder.btnViewNumber.setOnClickListener(contactListener);
        holder.btnWhatsapp.setOnClickListener(contactListener);
        holder.btnCall.setOnClickListener(contactListener);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, PropertyDetailActivity.class);
            intent.putExtra("property", property);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ViewPager2 viewPager;
        TabLayout tabLayout;
        TextView tvType, tvSubTitle, tvPrice, tvArea, tvDealerName, tvListingType;
        Button btnViewNumber;
        ImageButton btnWhatsapp, btnCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.propertyImagePager);
            tabLayout = itemView.findViewById(R.id.tabLayout);
            tvType = itemView.findViewById(R.id.tvPropertyName);
            tvSubTitle = itemView.findViewById(R.id.tvPropertySubTitle);
            tvPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvArea = itemView.findViewById(R.id.tvPropertyArea);
            tvDealerName = itemView.findViewById(R.id.tvDealerName);
            tvListingType = itemView.findViewById(R.id.tvListingType);
            btnViewNumber = itemView.findViewById(R.id.btnViewNumber);
            btnWhatsapp = itemView.findViewById(R.id.btnWhatsapp);
            btnCall = itemView.findViewById(R.id.btnCall);
        }
    }

    private static class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder> {
        private final Context context;
        private final List<String> images;

        public ImagePagerAdapter(Context context, List<String> images) {
            this.context = context;
            this.images = images;
        }

        @NonNull
        @Override
        public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ImageViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
            Glide.with(context)
                    .load(images.get(position))
                    .placeholder(android.R.drawable.ic_menu_gallery)
                    .into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return images != null ? images.size() : 0;
        }

        static class ImageViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public ImageViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }
        }
    }
}