package com.zhou.reader.base.recyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> datas;
    private int viewType;

    public BaseAdapter(List<T> datas, int viewType) {
        this.datas = datas;
        this.viewType = viewType;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolderFactory.get().createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        if (adapterPosition > 0 && adapterPosition < getItemCount()){
            T t = datas.get(holder.getAdapterPosition());
            holder.bindViewHolder(t);
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
