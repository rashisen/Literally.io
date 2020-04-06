package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.List;

public class LanguageSubscriptionActivity extends AppCompatActivity {
    private LanguageViewModel languageViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_subscription);

        //noinspection deprecation
        languageViewModel = ViewModelProviders.of(this).get(LanguageViewModel.class);
        languageViewModel.getAllLanguages().observe(this, languages -> {

            languages.forEach(item->{
                Log.i("languageName", item.getLangId());
            });
        });


    }

}
