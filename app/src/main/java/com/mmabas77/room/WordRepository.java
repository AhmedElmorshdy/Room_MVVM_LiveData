package com.mmabas77.room;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    LiveData<List<Word>>getAllWors ;


    public WordRepository(Application app) {
        WordRoomDB db = WordRoomDB.getInstance(app);
        wordDao = db.wordDao();
        getAllWors = wordDao.getLists();
    }

    public void insert(Word word){
        new InsertAsyncTask(wordDao).execute(word);

    }
    public void delete(Word word){
        new DeleteAsyncTask(wordDao).execute(word);

    }
    public void update(Word word){
        new UpdateAsyncTask(wordDao).execute(word);

    }
    public LiveData<List<Word>>getAllWords(){

        return wordDao.getLists();
    }
    public void deleteAllWord(){
        new DeleteAllAsyncTask(wordDao).execute();


    }
    private static class InsertAsyncTask extends AsyncTask<Word,Void,Void>{

        public WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Word,Void,Void>{

        public WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.update(words[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Word,Void,Void>{

        public WordDao wordDao;

        public DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.delete(words[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        public WordDao wordDao;

        public DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll();
            return null;
        }
    }


}
