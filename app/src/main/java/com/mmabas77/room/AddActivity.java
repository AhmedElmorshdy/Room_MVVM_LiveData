package com.mmabas77.room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    EditText wordEditText, meaningEditText, typeEditText;
    AddNewWordViewModel addNewWordViewModel;
    public static final String EXTRA_ID = "com.example.wordlist.extraid";
    public static final String EXTRA_WORD = "com.example.wordlist.word";
    public static final String EXTRA_MEANING = "com.example.wordlist.meaning";
    public static final String EXTRA_TYPE = "com.example.wordlist.type";
    Word wordObject;


    private boolean editMode;
    private int mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        wordEditText = findViewById(R.id.editTextTextPersonName);
        meaningEditText = findViewById(R.id.editTextTextPersonName2);
        typeEditText = findViewById(R.id.editTextTextPersonName3);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_exit_to_app_24);
        Intent i = getIntent();


        if (i.hasExtra(EXTRA_ID)){
            setTitle("update Activity");
            editMode=true;
           mID = i.getIntExtra(EXTRA_ID,-1);
            wordEditText.setText(i.getStringExtra(EXTRA_WORD).toString());
            meaningEditText.setText(i.getStringExtra(EXTRA_MEANING));
            typeEditText.setText(i.getStringExtra(EXTRA_TYPE));

        }else {
            setTitle("Add New Activity");
            editMode = false;
        }
        addNewWordViewModel = ViewModelProviders.of(this).get(AddNewWordViewModel.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saved();
                return true;
            default:
                return super.onOptionsItemSelected(item);}
        }

    private void saved() {
        String word = wordEditText.getText().toString().trim();
        String meaning = meaningEditText.getText().toString().trim();
        String type = typeEditText.getText().toString().trim();

         wordObject = new Word(word, meaning, type);

        if (word.isEmpty() || type.isEmpty() || meaning.isEmpty()) {
            Toast.makeText(AddActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();
            return;
        }

        if(editMode){
            wordObject.setId(mID);
            Toast.makeText(AddActivity.this, "please fill all fields", Toast.LENGTH_LONG).show();
            addNewWordViewModel.updateWord(wordObject);
        }else{
            addNewWordViewModel.insert(wordObject);
        }
        finish();
    }

}