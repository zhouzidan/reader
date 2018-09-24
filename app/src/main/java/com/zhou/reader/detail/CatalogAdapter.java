package com.zhou.reader.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.reader.R;
import com.zhou.reader.db.Catalog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CatalogAdapter  extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    private List<Catalog> catalogs;
    private LayoutInflater inflater;
    private CatalogAdapter.ClickCallback clickCallback;

    public CatalogAdapter(Context context, List<Catalog> catalogs) {
        this.catalogs = catalogs;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CatalogAdapter.CatalogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_catalog,parent,false);
        return new CatalogAdapter.CatalogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.CatalogViewHolder holder, int position) {
        final Catalog catalog = catalogs.get(holder.getAdapterPosition());
        holder.titleTextView.setText(catalog.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickCallback != null){
                    clickCallback.call(catalog);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogs.size();
    }

    public void setClickCallback(CatalogAdapter.ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    public static class CatalogViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.title)
        TextView titleTextView;

        public CatalogViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ClickCallback{
        void call(Catalog catalog);
    }
}
