package com.example.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
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
        holder.tvName.setText(property.getName());
        holder.tvLocation.setText(property.getLocation());
        holder.tvPrice.setText(property.getPrice());
        
        // Dynamic stats
        String stats = property.getBhk() + " | " + property.getFloors();
        holder.tvStats.setText(stats);

        Glide.with(context)
                .load(property.getImageUri())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.ivProperty);

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
        ImageView ivProperty;
        TextView tvName, tvLocation, tvPrice, tvStats;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProperty = itemView.findViewById(R.id.propertyImage);
            tvName = itemView.findViewById(R.id.tvPropertyName);
            tvLocation = itemView.findViewById(R.id.tvPropertyLocation);
            tvPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvStats = itemView.findViewById(R.id.tvPropertyStats);
        }
    }
}