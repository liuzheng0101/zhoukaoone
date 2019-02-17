package com.example.day3.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.day3.R;
import com.example.day3.bean.UserInfo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.Databean> list;
    public MyAdapter(Context context, List<UserInfo.Databean> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context, R.layout.item,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.content.setText(list.get(i).book_cover);
        viewHolder.title.setText(list.get(i).bookname);
        String[] spilt=list.get(i).book_info.split("!");
        Glide.with(context).load(spilt[0]).into(viewHolder.icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView icon;
        private TextView title,content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.icon);
            title=itemView.findViewById(R.id.title);
            content=itemView.findViewById(R.id.content);
        }
    }
}
