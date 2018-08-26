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
    private Context context;

    public BookListAdapter(Context context,List<Book> books) {
        this.books = books;
        this.context = context;
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
        Book book = books.get(holder.getAdapterPosition());
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthor());
        holder.introTextView.setText(book.getShortIntro());
        holder.typeTextView.setText(book.getCat());
        String imgUrl = "http://statics.zhuishushenqi.com"+book.getCover();
        GlideApp.with(holder.itemView)
                .load(imgUrl)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return books.size();
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


        @BindView(R.id.type)
        TextView typeTextView;


        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
