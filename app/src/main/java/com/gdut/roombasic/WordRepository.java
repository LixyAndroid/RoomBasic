package com.gdut.roombasic;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author Li Xuyang
 * date  2019/9/7 23:06
 */
class WordRepository {


    private LiveData<List<Word>> allWordsLive;
    private WordDao wordDao;

    WordRepository(Context context) {
        WordDatabase wordDatabase = WordDatabase.getDatabase(context.getApplicationContext());
        wordDao = wordDatabase.getWordDao();
        allWordsLive = wordDao.getAllWordsLive();
    }

    void insertWords(Word... words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word... words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word... words) {
        new DeleteAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords(Word... words) {
        new DeleteAllAsyncTask(wordDao).execute();
    }


    LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
        InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        private WordDao wordDao;


        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }

    }


    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {
        UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        private WordDao wordDao;


        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updataWords(words);
            return null;
        }

    }

    static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {
        DeleteAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        private WordDao wordDao;


        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }

    }

    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        DeleteAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        private WordDao wordDao;


        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }

    }

}
