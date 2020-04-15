package com.example.w1742120_cw02;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.List;

public class EditPhraseActivity extends AppCompatActivity {
    private PhraseViewModel phraseViewModel;
    private String text;
    private AlertDialog.Builder alertDialog;
    private EditPhraseAdapter adapter;
    private EditText input;
    private Phrase selectedPhrase;
    String phraseDescription;

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
        setContentView(R.layout.activity_edit_phrase);

        RecyclerView recyclerView = findViewById(R.id.displayallEditRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new EditPhraseAdapter(this);
        recyclerView.setAdapter(adapter);

        //noinspection deprecation
        phraseViewModel = ViewModelProviders.of(this).get(PhraseViewModel.class);
        phraseViewModel.getAllPhrases().observe(this, new Observer<List<Phrase>>() {
            @Override
            public void onChanged(List<Phrase> phrases) {
                //update recyclerview
                adapter.setPhrases(phrases);
            }
        });



    }

    public void editSelectedPhrase(View view) {
        alertDialog = new AlertDialog.Builder(EditPhraseActivity.this);
        alertDialog.setTitle("Edit Phrase");
        input = new EditText(EditPhraseActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Save Changes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text = input.getText().toString();
                        selectedPhrase = adapter.selectedPhrase;
                        selectedPhrase.setDescription(text);
                        phraseViewModel.update(selectedPhrase);

                    }
                });

        alertDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        phraseDescription = adapter.selectedPhrase.toString();
        input.setText(phraseDescription);
        alertDialog.show();

    }
}
