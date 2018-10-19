package com.zhou.reader;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elvishew.xlog.XLog;
import com.zhou.reader.db.Catalog;
import com.zhou.reader.db.CatalogDBManager;
import com.zhou.reader.read.BookContentUtil;
import com.zhou.reader.util.AppExecutor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.VH> {

    private LayoutInflater inflater;

    private long localBookId;
    private List<Catalog> catalogs ;

    public ReadAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public void setLocalBookId(long bookId) {
        this.localBookId = bookId;
        catalogs = CatalogDBManager.get().getAll(localBookId);
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(inflater.inflate(R.layout.item_content_read,null));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Catalog catalog = catalogs.get(position);
        if (TextUtils.isEmpty(catalog.getContent())){
            AppExecutor.get().networkIO().execute(() -> {
                Catalog tempCatalog = CatalogDBManager.get().findById(catalog.id);
                if (!TextUtils.isEmpty(tempCatalog.getContent())){
                    catalog.setContent(tempCatalog.getContent());
                }else {
                    BookContentUtil.loadBookContent(catalog);
                    CatalogDBManager.get().save(catalog);
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
        String content = "<H1>"+catalog.getTitle()+"</H1>" + catalog.getContent();
        holder.contentTextView.setText(Html.fromHtml(content));
    }

    @Override
    public int getItemCount() {
        return catalogs.size();
    }

    public int getPosition(Catalog catalog){
        return catalogs.indexOf(catalog);
    }

    public Catalog getCatalogByPosition(int position){
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
