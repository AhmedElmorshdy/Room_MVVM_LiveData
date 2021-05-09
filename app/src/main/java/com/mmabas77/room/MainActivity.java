package com.mmabas77.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordViewModel wordViewModel;
    RecyclerView recyclerView;
    WordAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    List<Word>words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recy);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

      wordViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
              .get(WordViewModel.class);
         wordViewModel.allWords.observe(this, new Observer<List<Word>>() {
             @Override
             public void onChanged(List<Word> words) {
                 adapter = new WordAdapter(MainActivity.this,words);
                 recyclerView.setAdapter(adapter);
                 adapter.notifyDataSetChanged();
             }
         });
      new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
          @Override
          public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
              return false;
          }

          @Override
          public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
              int ps =viewHolder.getAdapterPosition();

              wordViewModel.deleteWord(adapter.getWordAt(ps));

          }
      }).attachToRecyclerView(recyclerView);



    }

    public void bu(View view) {
        Intent i = new Intent(MainActivity.this,AddActivity.class);
        startActivity(i);
    }
}