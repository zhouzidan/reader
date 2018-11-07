package com.zhou.reader.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.reader.R;
import com.zhou.reader.db.Search;
import com.zhou.reader.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zhou.reader.util.DateUtil.DateFormatYYYYMMDDHHMMSS;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.VH> {

    List<Search> searches = new ArrayList<>();

    private CallBack<String> callBack;

    public SearchHistoryAdapter(List<Search> searches) {
        this.searches = searches;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_history,null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Search search = searches.get(position);
        holder.titleView.setText(search.content);
        holder.lastUpdateTimeTextView.setText(DateUtil.date2String(search.updateTime,DateFormatYYYYMMDDHHMMSS));
        holder.itemView.setOnLongClickListener(v -> {
            if (callBack != null){
                callBack.call(search.content);
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public void setCallBack(CallBack<String> callBack) {
        this.callBack = callBack;
    }

    public static class VH extends RecyclerView.ViewHolder{
        @BindView(R.id.title)
        public TextView titleView;

        @BindView(R.id.lastUpdateTime)
        public TextView lastUpdateTimeTextView;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
