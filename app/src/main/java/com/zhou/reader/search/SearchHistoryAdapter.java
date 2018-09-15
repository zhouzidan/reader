package com.zhou.reader.search;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhou.reader.R;
import com.zhou.reader.db.Search;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    private List<Search> searches;
    private LayoutInflater inflater;


    public SearchHistoryAdapter(List<Search> searches) {
        this.searches = searches;
    }


    @NonNull
    @Override
    public SearchHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null){
            inflater = LayoutInflater.from(parent.getContext());
        }
        View view = inflater.inflate(R.layout.item_search_history,parent,false);
        return new SearchHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHistoryViewHolder holder, int position) {
        Search search = searches.get(holder.getAdapterPosition());
        holder.textView.setText(search.content);
    }

    @Override
    public int getItemCount() {
        return searches.size();
    }

    public static class SearchHistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView textView;

        public SearchHistoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

    }
}
