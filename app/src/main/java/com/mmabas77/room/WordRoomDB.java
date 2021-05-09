package com.mmabas77.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Word.class,version = 1)
public abstract class WordRoomDB extends RoomDatabase {

    private static WordRoomDB instance;
    public abstract WordDao wordDao();

    public static synchronized WordRoomDB getInstance(Context context){
        if (instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext()
                    ,WordRoomDB.class,"word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDataAsyncTask(instance).execute();
        }
    };

    private static class populateDataAsyncTask extends AsyncTask<Void,Void,Void>{

        private WordDao wordDao;

        public populateDataAsyncTask(WordRoomDB db) {
            wordDao=db.wordDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.insert(new Word("Book","Book","noun"));
            wordDao.insert(new Word("Book","Book","noun"));
            wordDao.insert(new Word("Book","Book","noun"));

            return null;
        }
    }
}
