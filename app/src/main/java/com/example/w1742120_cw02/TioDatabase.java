package com.example.w1742120_cw02;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ibm.watson.language_translator.v3.LanguageTranslator;

import java.util.ArrayList;

@Database(entities = {Phrase.class, Language.class}, version = 2, exportSchema = false)
public abstract class TioDatabase extends RoomDatabase {
    private static final String LANG_API_KEY = "66Z8U81RxsQc9vOnoMptFC5VX3TFOgrg9yCjkAInZqhE";
    private static final String LANG_URL = "https://api.us-south.language-translator.watson.cloud.ibm.com/instances/8cc0fc59-c2a3-435d-916d-839db036f9ef";
    private static final String LANG_VERSION = "2018-05-01";

    /**
     * created because this class needs to be turned into a singleton
     * multiple instances of this class is not allowed
     */
    private static TioDatabase instance;

    public abstract PhraseDao phraseDao();

    public abstract LanguageDao languageDao();


    public static synchronized TioDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TioDatabase.class, "phrase_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };


    public static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private LanguageDao languageDao;

        private PopulateDbAsyncTask(TioDatabase db) {
            languageDao = db.languageDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            LanguageTranslator translationService = IBMCloudService.getLanguageTranslatorService(
                    LANG_API_KEY,
                    LANG_URL,
                    LANG_VERSION);

            Log.i("API", "Cred/" + LANG_API_KEY + " " + LANG_URL + " " + LANG_VERSION);

            ArrayList<Language> languages = new ArrayList<>();

            translationService
                    .listIdentifiableLanguages()
                    .execute()
                    .getResult()
                    .getLanguages()
                    .forEach(item -> {
                        languages.add(new Language(item.getLanguage(), item.getName()));
                        Log.i("langname", item.getName());
                    });

            for(Language language : languages){
                if (language.getLangId().equalsIgnoreCase("en")){
                    language.setCheckValue(1);
                    languageDao.insertlang(language);
                }else{
                    languageDao.insertlang(language);
                }
            }

            return null;
        }
    }
}
