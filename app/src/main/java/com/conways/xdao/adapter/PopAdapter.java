package com.conways.xdao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conways.xdao.Activity.AddActivity;
import com.conways.xdao.R;
import com.conways.xdao.holder.PopHolder;

import java.util.List;

/**
 * Created by John on 2016/9/21.
 */
public class PopAdapter extends RecyclerView.Adapter<PopHolder> {


    private List<String> list;
    private Context context;
    private ClickListenter listenter;

    public PopAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setListenter(ClickListenter listenter) {
        this.listenter = listenter;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public PopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pop_list, parent, false);
        return new PopHolder(view);
    }

    @Override
    public void onBindViewHolder(PopHolder holder, final int position) {
        holder.tvValue.setText(list.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == listenter)
                    return;
                listenter.click(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ClickListenter {
        public void click(int position);
    }
}
