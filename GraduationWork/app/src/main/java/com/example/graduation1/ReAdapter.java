package com.example.graduation1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Re> ReList;

    public ReAdapter(Context context, int layout, ArrayList<Re> ReList) { //어댑터 생성자
        this.context = context;
        this.layout = layout;
        this.ReList = ReList;
    }

    @Override
    public int getCount() {
        return ReList.size();
    }

    @Override
    public Object getItem(int i) {
        return ReList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{ //뷰홀더 이곳에 item들의 컴포넌트를 작성
        TextView user_text ,content_text ,time_text ;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //실질적으로 화면에 보여주게하는 메소드

        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row ==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.user_text = row.findViewById(R.id.user_text);
            holder.content_text = row.findViewById(R.id.content_text);
            holder.time_text = row.findViewById(R.id.time_text);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Re re = ReList.get(i);
        holder.user_text.setText(re.getUser()+" : ");
        holder.content_text.setText(re.getContent());
        holder.time_text.setText(re.getTime());

        return row;
    }


}
