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
        
        holder.tvType.setText(property.getPropertyType() != null ? property.getPropertyType().toUpperCase() : "PROPERTY");
        holder.tvSubTitle.setText(property.getBhk() + " House in " + property.getLocation());
        holder.tvPrice.setText(property.getPrice());
        holder.tvArea.setText(property.getSqft() != null ? property.getSqft() : "N/A");
        holder.tvDealerName.setText(property.getDealerName() != null ? property.getDealerName() : "Unknown Dealer");
        
        // Price per sqft logic or dummy
        holder.tvPricePerSqft.setText("₹ 26,013 /sqft");

        Glide.with(context)
                .load(property.getImageUri())
                .centerCrop()
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.ivProperty);

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
        ImageView ivProperty;
        TextView tvType, tvSubTitle, tvPrice, tvPricePerSqft, tvArea, tvDealerName;
        Button btnViewNumber;
        ImageButton btnWhatsapp, btnCall;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProperty = itemView.findViewById(R.id.propertyImage);
            tvType = itemView.findViewById(R.id.tvPropertyName);
            tvSubTitle = itemView.findViewById(R.id.tvPropertySubTitle);
            tvPrice = itemView.findViewById(R.id.tvPropertyPrice);
            tvPricePerSqft = itemView.findViewById(R.id.tvPricePerSqft);
            tvArea = itemView.findViewById(R.id.tvPropertyArea);
            tvDealerName = itemView.findViewById(R.id.tvDealerName);
            btnViewNumber = itemView.findViewById(R.id.btnViewNumber);
            btnWhatsapp = itemView.findViewById(R.id.btnWhatsapp);
            btnCall = itemView.findViewById(R.id.btnCall);
        }
    }
}