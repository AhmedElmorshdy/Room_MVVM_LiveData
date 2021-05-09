package com.mmabas77.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);

    }
    public void insert(Word word){
        wordRepository.insert(word);
    }
    public void updateWord(Word word){
        wordRepository.update(word);
    }

}
