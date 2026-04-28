package com.example.realestateapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import java.util.List;

public class PropertyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        Property property = (Property) getIntent().getSerializableExtra("property");

        if (property != null) {
            // Setup Image Pager
            ViewPager2 imagePager = findViewById(R.id.detailImagePager);
            TabLayout tabLayout = findViewById(R.id.detailTabLayout);
            ImagePagerAdapter adapter = new ImagePagerAdapter(this, property.getAdditionalImages());
            imagePager.setAdapter(adapter);
            new TabLayoutMediator(tabLayout, imagePager, (tab, position) -> {}).attach();

            // Dealer Info
            TextView tvDealerName = findViewById(R.id.tvDetailDealerName);
            TextView tvDaysAgo = findViewById(R.id.tvDetailDaysAgo);
            tvDealerName.setText(property.getDealerName());
            tvDaysAgo.setText(property.getDealerDaysAgo());

            // Price and Basic Info
            TextView tvPrice = findViewById(R.id.tvDetailPrice);
            TextView tvPriceInfo = findViewById(R.id.tvPriceInfo);
            TextView tvName = findViewById(R.id.tvDetailName);
            TextView tvLocation = findViewById(R.id.tvDetailLocation);
            TextView tvListingInfo = findViewById(R.id.tvListingInfo);

            tvPrice.setText(property.getPrice());
            tvPriceInfo.setText(property.getPriceInfo() + "... See Price details");
            tvName.setText(property.getName());
            tvLocation.setText(property.getLocation());
            tvListingInfo.setText(property.getPropertyType() + " for " + property.getListingType() + " in");

            // Tags
            findViewById(R.id.tagFurnishing).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.tagFurnishing)).setText(property.getFurnishingStatus());

            // Quick Stats
            ((TextView)findViewById(R.id.tvStatBhk)).setText(property.getBhk() + " and " + property.getBaths());
            ((TextView)findViewById(R.id.tvStatArea)).setText(property.getSuperArea() + " Super Area");
            ((TextView)findViewById(R.id.tvStatPriceSqft)).setText(property.getPricePerSqft() + " Super Area");
            ((TextView)findViewById(R.id.tvStatAge)).setText(property.getPropertyAge());

            // Table Details
            ((TextView)findViewById(R.id.tableLayout)).setText(property.getBhk() + ", " + property.getBaths() + ", Pooja Room...");
            ((TextView)findViewById(R.id.tableOwnership)).setText(property.getOwnership());
            ((TextView)findViewById(R.id.tableSuperArea)).setText(property.getSuperArea());
            ((TextView)findViewById(R.id.tableFacing)).setText(property.getFacing());

            // Description
            TextView tvDescription = findViewById(R.id.tvDetailDescription);
            tvDescription.setText(property.getDescription());

            // Nearby Places
            LinearLayout layoutNearby = findViewById(R.id.layoutNearbyPlaces);
            for (String place : property.getNearbyPlaces()) {
                TextView chip = new TextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, 0, 12, 0);
                chip.setLayoutParams(params);
                chip.setBackgroundResource(R.drawable.search_tab_selected); // Reuse a light blue bg
                chip.setPadding(24, 12, 24, 12);
                chip.setText(place);
                chip.setTextColor(getResources().getColor(R.color.primary));
                chip.setTextSize(12);
                layoutNearby.addView(chip);
            }

            // Bottom Actions
            findViewById(R.id.btnDetailWhatsapp).setOnClickListener(v -> openWhatsApp());
            findViewById(R.id.btnDetailViewNumber).setOnClickListener(v -> {
                Intent intent = new Intent(this, ShareDetailsActivity.class);
                intent.putExtra("mode", "call");
                startActivity(intent);
            });
            findViewById(R.id.btnDetailCall).setOnClickListener(v -> makeCall());
        }

        findViewById(R.id.toolbar).setOnClickListener(v -> finish());
    }

    private void openWhatsApp() {
        try {
            String url = "https://api.whatsapp.com/send?phone=918307901188&text=Hi, I am interested in your property listing.";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:+918307901188"));
        startActivity(intent);
    }

    private static class ImagePagerAdapter extends RecyclerView.Adapter<ImagePagerAdapter.ViewHolder> {
        private final List<String> images;
        private final Context context;

        public ImagePagerAdapter(Context context, List<String> images) {
            this.context = context;
            this.images = images;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return new ViewHolder(imageView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context).load(images.get(position)).into(holder.imageView);
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imageView = (ImageView) itemView;
            }
        }
    }
}
