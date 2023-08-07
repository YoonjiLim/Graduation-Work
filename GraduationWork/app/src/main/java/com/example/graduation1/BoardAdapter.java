package com.example.graduation1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Board> boardList;

    public BoardAdapter(Context context, int layout, ArrayList<Board> diaryList) { //어댑터 생성자
        this.context = context;
        this.layout = layout;
        this.boardList = diaryList;
    }

    @Override
    public int getCount() {
        return boardList.size();
    }

    @Override
    public Object getItem(int i) {
        return boardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{ //뷰홀더 이곳에 item들의 컴포넌트를 작성
        TextView title_text ,content_text ,time_text ;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) { //실질적으로 화면에 보여주게하는 메소드

        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row ==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.title_text = row.findViewById(R.id.title_text);
            holder.content_text = row.findViewById(R.id.content_text);
            holder.time_text = row.findViewById(R.id.time_text);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }

        Board board = boardList.get(i);
        holder.title_text.setText(board.getTitle());
        holder.content_text.setText(board.getContent());
        holder.time_text.setText(board.getTime());

        return row;
    }


}
