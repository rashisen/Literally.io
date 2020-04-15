package com.example.w1742120_cw02;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddPhraseActivity extends AppCompatActivity {
    private TextInputLayout addedDescription;
    private TextInputEditText editText;
    private PhraseViewModel phraseViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorSecondary));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        setContentView(R.layout.activity_add_phrase);

        //noinspection deprecation
        phraseViewModel = ViewModelProviders.of(this).get(PhraseViewModel.class);
        addedDescription = findViewById(R.id.addTextField);
        editText = findViewById(R.id.addPhraseETV);
    }

    public void addPhrase(View view) {
        String description = addedDescription.getEditText().getText().toString();

        if (description.trim().isEmpty()){
            Snackbar.make(
                    view,
                    "Please enter the phrase you want to save.",
                    Snackbar.LENGTH_LONG

            ).show();
            return;
        }

        Phrase phrase = new Phrase(description);
        Log.i(String.valueOf(phrase), "extracted phrase");
        phraseViewModel.insert(phrase);

        Snackbar.make(
                view,
                "Phrase Saved",
                Snackbar.LENGTH_SHORT

        ).show();
    }


}
