package com.example.w1742120_cw02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.card.MaterialCardView;

public class MainMenuActivity extends AppCompatActivity {

    MaterialCardView add,edit,display,subscribe,translate;

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

        setContentView(R.layout.activity_main_menu);

        add = findViewById(R.id.btn_main_add_phrase);
        add.setOnClickListener(this::addPhrasePage);

        edit = findViewById(R.id.btn_main_edit_phrase);
        edit.setOnClickListener(this::editPhrase);

        display = findViewById(R.id.btn_main_disp_phrase);
        display.setOnClickListener(this::displayPhrase);

        subscribe = findViewById(R.id.btn_main_subscrip);
        subscribe.setOnClickListener(this::languageSubscription);

        translate = findViewById(R.id.btn_main_translate);
        translate.setOnClickListener(this::translate);

    }

    //add phrase activity
    public void addPhrasePage(View view) {
        Intent intent = new Intent(MainMenuActivity.this, AddPhraseActivity.class);
        startActivity(intent);
    }

    //display phrase activity
    public void displayPhrase(View view) {
        Intent intent = new Intent(MainMenuActivity.this, DisplayPhraseActivity.class);
        startActivity(intent);
    }

    //edit phrase activity
    public void editPhrase(View view) {
        Intent intent = new Intent(MainMenuActivity.this, EditPhraseActivity.class);
        startActivity(intent);
    }

    //language subscription activity
    public void languageSubscription(View view) {
        Intent intent = new Intent(MainMenuActivity.this, LanguageSubscriptionActivity.class);
        startActivity(intent);
    }

    //translate activity
    public void translate(View view) {
        Intent intent = new Intent(MainMenuActivity.this, TranslateActivity.class);
        startActivity(intent);
    }
}
