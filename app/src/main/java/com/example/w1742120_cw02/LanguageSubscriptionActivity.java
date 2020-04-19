package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LanguageSubscriptionActivity extends AppCompatActivity {
    private LanguageViewModel languageViewModel;
    private MaterialCheckBox checkBox;
    private Language lang;
    private ArrayList<Language> langList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorSecondary));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_language_subscription);
        checkBox = findViewById(R.id.langCheck);

        //recycler view
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
                langList.addAll(languages);

            }
        });

        /**
         * function to change checkbox value when clicked
         */
        adapter.setOnCardClickListener(new LangSubscripAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(Language language) {
                 if (language.getCheckValue()==0){
                     for (Language lang: langList) {
                         if (lang.getLangId().equals(language.getLangId())){
                             lang.setCheckValue(1);
                         }
                     }

                 }
                 else if (language.getCheckValue() == 1){
                     for (Language lang: langList) {
                         if (lang.getLangId().equals(language.getLangId())){
                             lang.setCheckValue(0);
                         }
                     }

                 }
            }
        });


    }

    /**
     * Function executed when the subscribe button is clicked
     * @param view
     */
    public void SubscribeLang(View view) {
        for (Language lang: langList) {
            languageViewModel.update(lang);

        }

        Snackbar.make(
                view,
                "Changes for Subscriptions saved.",
                Snackbar.LENGTH_SHORT

        ).show();
    }
}
