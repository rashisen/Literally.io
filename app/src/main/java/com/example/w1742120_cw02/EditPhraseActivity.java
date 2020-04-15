package com.example.w1742120_cw02;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
