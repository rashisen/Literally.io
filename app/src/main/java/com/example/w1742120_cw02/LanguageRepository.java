package com.example.w1742120_cw02;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LanguageRepository {

    private LanguageDao languageDao;
    private LiveData<List<Language>> allLanguages;

    public LanguageRepository(Application application){
        TioDatabase tioDatabase = TioDatabase.getInstance(application);
        languageDao = tioDatabase.languageDao();
        allLanguages = languageDao.getAllLanguages();
    }

    public void insert(Language language){
        new InsertLangAsyncTask(languageDao).execute(language);
    }

    public void update(Language language){
        new UpdateLangAsyncTask(languageDao).execute(language);
    }

    public void delete(Language language){
        new DeleteLangAsyncTask(languageDao).execute(language);
    }

    public LiveData<List<Language>> getAllLanguages(){
        return allLanguages;
    }

    private static class InsertLangAsyncTask extends AsyncTask<Language,Void,Void> {
        private LanguageDao languageDao;

        private InsertLangAsyncTask(LanguageDao languageDao){
            this.languageDao = languageDao;
        }

        @Override
        protected Void doInBackground(Language... languages) {
            languageDao.insertlang(languages[0]);
            return null;
        }
    }

    private static class UpdateLangAsyncTask extends AsyncTask<Language,Void,Void> {
        private LanguageDao languageDao;

        private UpdateLangAsyncTask(LanguageDao languageDao){
            this.languageDao = languageDao;
        }

        @Override
        protected Void doInBackground(Language... languages) {
            languageDao.updatelang(languages[0]);
            return null;
        }
    }

    private static class DeleteLangAsyncTask extends AsyncTask<Language,Void,Void> {
        private LanguageDao languageDao;

        private DeleteLangAsyncTask(LanguageDao languageDao){
            this.languageDao = languageDao;
        }

        @Override
        protected Void doInBackground(Language... languages) {
            languageDao.deletelang(languages[0]);
            return null;
        }
    }


    private static class DeleteAllLangAsyncTask extends AsyncTask<Void,Void,Void> {
        private LanguageDao languageDao;

        private DeleteAllLangAsyncTask(LanguageDao languageDao){
            this.languageDao = languageDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            languageDao.deleteAllLanguages();
            return null;
        }
    }


}
