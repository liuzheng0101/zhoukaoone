package com.example.zhoukao1.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoukao1.R;
import com.example.zhoukao1.activity.MainActivity;
import com.example.zhoukao1.bean.UserInfo;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class HomeAdapter extends XRecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<UserInfo.shopData> list;
    public HomeAdapter(Context context, List<UserInfo.shopData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.name.setText(list.get(position).commodityName);
        holder.price.setText(list.get(position).masterPic);
        //fresco 加载图片
        Uri uri=Uri.parse(list.get(position).masterPic);
        DraweeController controller=Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(30f);
        holder.icon.getHierarchy().setRoundingParams(roundingParams);
        holder.icon.setController(controller);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickitem!=null){
                    onclickitem.getId(list.get(position).commodityId);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size()>0?list.size():0;
    }
    public void addData(List<UserInfo.shopData> result) {
        if (result!=null){
            list.addAll(result);
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView icon;
        private TextView name,price;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=itemView.findViewById(R.id.item_image);
            name=itemView.findViewById(R.id.itemtv_name);
            price=itemView.findViewById(R.id.itemtv_price);
        }
    }
    public interface onClickitem{
        void getId(String id);
    }
    private onClickitem onclickitem;
    public void setOnclickitem(onClickitem onclickitem) {
        this.onclickitem = onclickitem;
    }
}
