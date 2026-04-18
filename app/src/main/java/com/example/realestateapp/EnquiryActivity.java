package com.example.realestateapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class EnquiryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiries);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        RecyclerView rvEnquiries = findViewById(R.id.rvEnquiries);
        rvEnquiries.setLayoutManager(new LinearLayoutManager(this));

        List<Enquiry> enquiries = EnquiryManager.getEnquiries();
        rvEnquiries.setAdapter(new EnquiryAdapter(enquiries));
    }

    private static class EnquiryAdapter extends RecyclerView.Adapter<EnquiryAdapter.ViewHolder> {
        private List<Enquiry> enquiries;

        public EnquiryAdapter(List<Enquiry> enquiries) {
            this.enquiries = enquiries;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enquiry, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Enquiry enquiry = enquiries.get(position);
            holder.tvPropertyName.setText(enquiry.getPropertyName());
            holder.tvUserName.setText("Sent by: " + enquiry.getUserName());
            holder.tvMessage.setText("Message: " + enquiry.getMessage());
        }

        @Override
        public int getItemCount() {
            return enquiries.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView tvPropertyName, tvUserName, tvMessage;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvPropertyName = itemView.findViewById(R.id.tvPropertyName);
                tvUserName = itemView.findViewById(R.id.tvUserName);
                tvMessage = itemView.findViewById(R.id.tvMessage);
            }
        }
    }
}
