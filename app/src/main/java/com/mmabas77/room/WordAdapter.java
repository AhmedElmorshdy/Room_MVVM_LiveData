package com.mmabas77.room;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.ViewDataHolder> {
    Context context;
    List<Word>words;



    public WordAdapter(Context context, List<Word> words) {
        this.context = context;
        this.words = words;
    }

    @NonNull
    @Override
    public ViewDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design,parent,false);
        return new ViewDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewDataHolder holder, final int position) {
        Word current = words.get(position);
        holder.bookname.setText(current.getWordName());
        holder.bookMeaning.setText(current.getWordMeaning());
        holder.type.setText(current.getWordType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,AddActivity.class);
                i.putExtra(AddActivity.EXTRA_ID, current.getId());
                i.putExtra(AddActivity.EXTRA_WORD, current.getWordName());
                i.putExtra(AddActivity.EXTRA_TYPE, current.getWordType());
                i.putExtra(AddActivity.EXTRA_MEANING, current.getWordMeaning());
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class ViewDataHolder extends RecyclerView.ViewHolder{
        TextView bookname,bookMeaning,type;
        CardView cardView;

        public ViewDataHolder(@NonNull View itemView) {
            super(itemView);
            bookname = itemView.findViewById(R.id.textView);
            bookMeaning = itemView.findViewById(R.id.textView2);
            type = itemView.findViewById(R.id.textView3);
            cardView = itemView.findViewById(R.id.card);


        }
    }

    public Word getWordAt(int pos)
    {
        return words.get(pos);
    }

}
