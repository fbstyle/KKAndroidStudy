package com.kkandroidstudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kkandroidstudy.R;

import java.util.List;

/**
 * Created by shiyan on 2016/9/30.
 */
public class AppBarLayoutAdapter extends RecyclerView.Adapter<AppBarLayoutAdapter.MyViewHolder> {
    private Context context;
    private List<String> list;

    public AppBarLayoutAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_appbarlayout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("getAdapterPosition 1", holder.getAdapterPosition() + "");
                list.remove(holder.getAdapterPosition());
                Log.e("getAdapterPosition 2", holder.getAdapterPosition() + "");
                notifyItemRemoved(holder.getAdapterPosition());
                Log.e("getAdapterPosition 3", holder.getAdapterPosition() + "");
                Log.e("holder.getLayoutPosi", holder.getLayoutPosition() + "");
                Log.e(" getItemCount()", getItemCount() + "");
                notifyItemRangeChanged(holder.getLayoutPosition(), getItemCount() - holder.getLayoutPosition());
            }
        });
        //设置数据源
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
