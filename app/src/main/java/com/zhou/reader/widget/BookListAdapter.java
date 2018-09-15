package com.zhou.reader.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhou.reader.GlideApp;
import com.zhou.reader.R;
import com.zhou.reader.entity.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {

    private List<Book> books;
    private LayoutInflater inflater;
    private ClickCallback clickCallback;

    public BookListAdapter(Context context,List<Book> books) {
        this.books = books;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_book,parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        final Book book = books.get(holder.getAdapterPosition());
        holder.titleTextView.setText(book.getTitle());
        holder.introTextView.setText(book.getDesc());
        holder.authorTextView.setText(book.getAuthor());
        holder.lastUpdateTimeTextView.setText(book.getUpdateTime()+"");

        String imgUrl = book.getCoverPic();
        GlideApp.with(holder.itemView)
                .load(imgUrl)
                .centerCrop()
                .into(holder.imageView);
        holder.itemView.setOnClickListener(v -> {
            if (clickCallback != null){
                clickCallback.call(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setClickCallback(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.img)
        ImageView imageView;

        @BindView(R.id.title)
        TextView titleTextView;


        @BindView(R.id.intro)
        TextView introTextView;


        @BindView(R.id.author)
        TextView authorTextView;

        @BindView(R.id.lastUpdateTime)
        TextView lastUpdateTimeTextView;


        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface ClickCallback{
        void call(Book book);
    }
}
