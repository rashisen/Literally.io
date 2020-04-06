package com.example.w1742120_cw02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.google.android.material.textfield.TextInputLayout;

public class AddPhraseActivity extends AppCompatActivity {
    private TextInputLayout addedDescription;
    private PhraseViewModel phraseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phrase);

        //noinspection deprecation
        phraseViewModel = ViewModelProviders.of(this).get(PhraseViewModel.class);
        addedDescription = findViewById(R.id.addTextField);
    }

    public void addPhrase(View view) {
        savePhrase();
    }

    private void savePhrase(){
        String description = addedDescription.getEditText().getText().toString();

        if (description.trim().isEmpty()){
            Toast.makeText(this,"Please enter the phrase you want to save.",Toast.LENGTH_SHORT).show();
            return;
        }

        Phrase phrase = new Phrase(description);
        Log.i(String.valueOf(phrase), "extracted phrase");
        phraseViewModel.insert(phrase);

        Toast.makeText(this,"Phrase Saved",Toast.LENGTH_SHORT).show();
    }
}
