package com.example.w1742120_cw02;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LanguageViewModel extends AndroidViewModel {
    private  LanguageRepository languageRepository;
    private LiveData<List<Language>> allLanguages;
    private LiveData<List<Language>> allSubscribedLanguages;

    public LanguageViewModel(@NonNull Application application) {
        super(application);
        languageRepository = new LanguageRepository(application);
        allLanguages = languageRepository.getAllLanguages();
        allSubscribedLanguages = languageRepository.getSubscribedLanguages();
    }

    public void insert(Language language){
        languageRepository.insert(language);
    }

    public void update(Language language){
        languageRepository.update(language);
    }

    public void delete(Language language){
        languageRepository.delete(language);
    }

    public LiveData<List<Language>> getAllLanguages() {
        return allLanguages;
    }

    public LiveData<List<Language>> getAllSubscribedLanguages() {
        return allLanguages;
    }

    public void setCheckState(Language language){
        languageRepository.updateCheck(language);
    }
}
