package com.example.graduation1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private List<Contact> dataSet;
    private OnCallButtonClickListener listener;

    private ImageButton buttonCall;

    public interface OnCallButtonClickListener {
        void onCallButtonClick(String phoneNumber);
    }

    public void setOnCallButtonClickListener(OnCallButtonClickListener listener) {
        this.listener = listener;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName;
        public TextView textAddress;
        public TextView textPhoneNumber;

        public TextView textArea;

        public ImageButton buttonCall;

        public ViewHolder(View view) {
            super(view);
            textArea = view.findViewById(R.id.textArea);
            textName = view.findViewById(R.id.textName);
            textAddress = view.findViewById(R.id.textAddress);
            textPhoneNumber = view.findViewById(R.id.textPhoneNumber);
            buttonCall=view.findViewById(R.id.buttonCall);
        }
    }

    public CustomAdapter(List<Contact> data) {
        dataSet = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.center_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Contact contact = dataSet.get(position);
        holder.textArea.setText(contact.getArea());
        holder.textName.setText(contact.getName());
        holder.textAddress.setText(contact.getAddress());
        holder.textPhoneNumber.setText(contact.getPhoneNumber());
        holder.buttonCall.setOnClickListener(v -> {
            if(listener!=null){
                String phoneNumber=contact.getPhoneNumber();
                listener.onCallButtonClick(phoneNumber);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}