package com.example.w1742120_cw02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetSavedPhrase extends AppCompatActivity {
    private PhraseViewModel phraseViewModel;
    private String selctedPhrase;
    public static final String EXTRA_PHRASE = "com.example.w1742120_cw02.EXTRA_PHRASE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_phrase);

        RecyclerView recyclerView = findViewById(R.id.displayallRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AddPhraseAdapter adapter = new AddPhraseAdapter();
        recyclerView.setAdapter(adapter);

        //noinspection deprecation
        phraseViewModel = ViewModelProviders.of(this).get(PhraseViewModel.class);
        phraseViewModel.getAllPhrases().observe(this, new Observer<List<Phrase>>() {
            @Override
            public void onChanged(List<Phrase> phrases) {
                adapter.setPhrases(phrases);
            }
        });

        adapter.setOnCardClickListener(new AddPhraseAdapter.OnCardClickListener() {
            @Override
            public void onCardClick(Phrase phrase) {
                Log.i("textval", "/PRE"+phrase.getDescription());
                Intent intent = new Intent();
                selctedPhrase = phrase.getDescription();
                intent.putExtra(EXTRA_PHRASE,selctedPhrase);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
