package com.example.w1742120_cw02;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhraseViewModel extends AndroidViewModel {
    private PhraseRepository repository;
    private LiveData<List<Phrase>> allPhrases;

    public PhraseViewModel(@NonNull Application application) {
        super(application);
        repository = new PhraseRepository(application);
        allPhrases = repository.getAllPhrases();
    }

    public void insert(Phrase phrase){
        repository.insert(phrase);
    }

    public void update(Phrase phrase){
        repository.update(phrase);
    }

    public void delete(Phrase phrase){
        repository.delete(phrase);
    }

    public void deleteAllPhrases(){
        repository.deleteAllPhrases();
    }

    public LiveData<List<Phrase>> getAllPhrases() {
        return allPhrases;
    }



}
