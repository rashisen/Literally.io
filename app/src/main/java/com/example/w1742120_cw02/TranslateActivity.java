package com.example.w1742120_cw02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import static com.example.w1742120_cw02.GetSavedPhrase.EXTRA_PHRASE;

public class TranslateActivity extends AppCompatActivity {
    public static final int PHRASE_REQUEST = 1;
    private TextInputEditText phraseBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        phraseBox = findViewById(R.id.tPhrase);
    }

    public void getSavedPhrases(View view) {
        Intent intent = new Intent(TranslateActivity.this, DisplayPhraseActivity.class);
        startActivityForResult(intent, PHRASE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==PHRASE_REQUEST && resultCode == RESULT_OK && !data.getStringExtra(EXTRA_PHRASE).equalsIgnoreCase("")){
            String phrase = data.getStringExtra(EXTRA_PHRASE);
            phraseBox.setText(phrase);
        }else {
            Snackbar.make(phraseBox, "Could not retreive phrase", Snackbar.LENGTH_SHORT);
        }
    }
}
