package com.example.w1742120_cw02;
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhraseRepository {

    private PhraseDao phraseDao;
    private LiveData<List<Phrase>> allPhrases;

    public PhraseRepository(Application application){
        TioDatabase tioDatabase = TioDatabase.getInstance(application);
        phraseDao = tioDatabase.phraseDao();
        allPhrases = phraseDao.getAllPhrases();
    }

    public void insert(Phrase phrase){
        new InsertPhraseAsyncTask(phraseDao).execute(phrase);
    }

    public void update(Phrase phrase){
        new UpdatePhraseAsyncTask(phraseDao).execute(phrase);
    }

    public void delete(Phrase phrase){
        new DeletePhraseAsyncTask(phraseDao).execute(phrase);
    }

    public void deleteAllPhrases(){
        new DeleteAllPhraseAsyncTask(phraseDao).execute();
    }

    public LiveData<List<Phrase>> getAllPhrases(){
        return allPhrases;
    }



    private static class InsertPhraseAsyncTask extends AsyncTask<Phrase,Void,Void>{
        private PhraseDao phraseDao;

        private InsertPhraseAsyncTask(PhraseDao phraseDao){
            this.phraseDao = phraseDao;
        }


        @Override
        protected Void doInBackground(Phrase... phrases) {
            phraseDao.insertPhrase(phrases[0]);
            return null;
        }
    }


    private static class UpdatePhraseAsyncTask extends AsyncTask<Phrase,Void,Void>{
        private PhraseDao phraseDao;

        private UpdatePhraseAsyncTask(PhraseDao phraseDao){
            this.phraseDao = phraseDao;
        }


        @Override
        protected Void doInBackground(Phrase... phrases) {
            phraseDao.updatePhrase(phrases[0]);
            return null;
        }
    }


    private static class DeletePhraseAsyncTask extends AsyncTask<Phrase,Void,Void>{
        private PhraseDao phraseDao;

        private DeletePhraseAsyncTask(PhraseDao phraseDao){
            this.phraseDao = phraseDao;
        }


        @Override
        protected Void doInBackground(Phrase... phrases) {
            phraseDao.deletePhrase(phrases[0]);
            return null;
        }
    }



    private static class DeleteAllPhraseAsyncTask extends AsyncTask<Void,Void,Void>{
        private PhraseDao phraseDao;

        private DeleteAllPhraseAsyncTask(PhraseDao phraseDao){
            this.phraseDao = phraseDao;
        }


        @Override
        protected Void doInBackground(Void... voids) {
            phraseDao.deleteAllPhrases();
            return null;
        }
    }
}
