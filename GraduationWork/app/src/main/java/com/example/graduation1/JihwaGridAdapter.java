package com.example.graduation1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;


public class JihwaGridAdapter extends BaseAdapter {
    ArrayList<Jihwa> items = new ArrayList<>();

    public int getCount() {
        return items.size();
    }

    public void addItem(Jihwa item){
        items.add(item);
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();
        final Jihwa jihwa = items.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.jihwa_item, viewGroup, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        ImageView jihwaImg = convertView.findViewById(R.id.jihwaImg);

        name.setText(jihwa.getName());
        jihwaImg.setImageResource(jihwa.getJihwaImg());

        return convertView;
    }
}
