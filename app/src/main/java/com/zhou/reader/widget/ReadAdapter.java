package com.zhou.reader.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.reader.R;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.ui.read.BookContentUtil;
import com.zhou.reader.ui.setting.ReadSettingManager;
import com.zhou.reader.ui.setting.ThemeManager;
import com.zhou.reader.util.AppExecutor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.VH> {

    private LayoutInflater inflater;

    private List<Catalog> catalogs = new ArrayList<>();

    public ReadAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setCatalogs(List<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(inflater.inflate(R.layout.item_content_read,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.contentTextView.setBackgroundColor(ThemeManager.getInstance().getReadContentBackgroundColor());
        holder.contentTextView.setTextColor(ThemeManager.getInstance().getReadContentTextColor());
        int textSize = ReadSettingManager.getInstance().getTextSize();
        holder.contentTextView.setTextSize(textSize);
        Catalog catalog = catalogs.get(position);
        if (TextUtils.isEmpty(catalog.getContent())){
            AppExecutor.get().networkIO().execute(() -> {
                if (catalog.getId() > 0){
                    Catalog tempCatalog = CatalogDBManager.get().findById(catalog.id);
                    if (!TextUtils.isEmpty(tempCatalog.getContent())){
                        catalog.setContent(tempCatalog.getContent());
                    }
                }
                if (TextUtils.isEmpty(catalog.getContent())){
                    BookContentUtil.loadBookContent(catalog);
                    if (catalog.getId() > 0){
                        CatalogDBManager.get().save(catalog);
                    }
                }
                AppExecutor.get().mainThread().execute(() -> {
                    showContent(catalog,holder);
                });
            });
        }else {
            showContent(catalog,holder);
        }
    }

    private void showContent(Catalog catalog , VH holder){
        String content = "<H1>"+catalog.getTitle()+"</H1>" + catalog.getContent() + "<BR/><BR/><BR/><BR/><BR/><BR/><BR/><BR/>";
        holder.contentTextView.setText(Html.fromHtml(content));
    }

    @Override
    public int getItemCount() {
        return catalogs != null ? catalogs.size() : 0;
    }

    public int getPosition(Catalog catalog){
        return catalogs != null ? catalogs.indexOf(catalog) : -1;
    }

    public Catalog getCatalogByPosition(int position){
        if (position < 0 && catalogs != null && position >= catalogs.size())
            position = 0;
        return catalogs.get(position);
    }

    public static class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.content_read)
        TextView contentTextView;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
