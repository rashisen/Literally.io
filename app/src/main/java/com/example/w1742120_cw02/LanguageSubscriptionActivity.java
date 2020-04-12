package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;



import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class LanguageSubscriptionActivity extends AppCompatActivity {
    private LanguageViewModel languageViewModel;
    private MaterialCheckBox checkBox;
    private LiveData<List<Language>> languageList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_subscription);
        checkBox = findViewById(R.id.langCheck);

        RecyclerView recyclerView = findViewById(R.id.langSubscrips);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        LangSubscripAdapter adapter = new LangSubscripAdapter();
        recyclerView.setAdapter(adapter);

        //noinspection deprecation
        languageViewModel = ViewModelProviders.of(this).get(LanguageViewModel.class);
        languageViewModel.getAllLanguages().observe(this, new Observer<List<Language>>() {
            @Override
            public void onChanged(List<Language> languages) {
                adapter.setLanguages(languages);

            }
        });

        adapter.setOnCardClickListener(new LangSubscripAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(Language language) {
                 if (language.getCheckValue()==0){
                     Language lang = new Language(language.getLangId(),language.getLanguage());
                     lang.setCheckValue(1);
                     languageViewModel.update(lang);
                 }
                 else if (language.getCheckValue() == 1){
                     Language langx = new Language(language.getLangId(),language.getLanguage());
                     langx.setCheckValue(0);
                     languageViewModel.update(langx);
                 }
            }
        });


    }

}
