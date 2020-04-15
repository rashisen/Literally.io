package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


    }

    public void addPhrasePage(View view) {
        Intent intent = new Intent(MainMenuActivity.this, AddPhraseActivity.class);
        startActivity(intent);
    }

    public void displayPhrase(View view) {
        Intent intent = new Intent(MainMenuActivity.this, DisplayPhraseActivity.class);
        startActivity(intent);
    }

    public void editPhrase(View view) {
        Intent intent = new Intent(MainMenuActivity.this, EditPhraseActivity.class);
        startActivity(intent);
    }

    public void languageSubscription(View view) {
        Intent intent = new Intent(MainMenuActivity.this, LanguageSubscriptionActivity.class);
        startActivity(intent);
    }

    public void translate(View view) {
        Intent intent = new Intent(MainMenuActivity.this, TranslateActivity.class);
        startActivity(intent);
    }
}
