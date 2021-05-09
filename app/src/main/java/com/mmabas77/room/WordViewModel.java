package com.mmabas77.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    public LiveData<List<Word>>allWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        allWords =wordRepository.getAllWords();
    }
    public void insert(Word word){
        wordRepository.insert(word);
    }
    public void deleteWord(Word word){
        wordRepository.delete(word);
    }
    public void updateWord(Word word){
        wordRepository.update(word);
    }

    public void deleteAllWord(){
        wordRepository.deleteAllWord();
    }
    public LiveData<List<Word>>getAllWords(){
        return allWords;
    }

}
