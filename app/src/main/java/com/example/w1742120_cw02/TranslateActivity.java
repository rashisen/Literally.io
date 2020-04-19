package com.example.w1742120_cw02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;
import com.ibm.cloud.sdk.core.http.HttpMediaType;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.developer_cloud.android.library.audio.StreamPlayer;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;

import java.util.ArrayList;

import static com.example.w1742120_cw02.GetSavedPhrase.EXTRA_PHRASE;

public class TranslateActivity extends AppCompatActivity {
    public static final int PHRASE_REQUEST = 1;
    private TextInputEditText phraseBox;
    private static MaterialTextView translatedPhraseView;
    private Spinner langSpinner;
    private ArrayList<String> langIdList = new ArrayList<>();
    private ArrayList<String> langNameList = new ArrayList<>();
    private static LanguageTranslator translationService;
    private static String langSelected;
    private static StreamPlayer player = new StreamPlayer();
    private static TextToSpeech speechService;
    private static TranslationResult result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorSecondary));
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        setContentView(R.layout.activity_translate);
        phraseBox = findViewById(R.id.tPhrase);
        translatedPhraseView = findViewById(R.id.displayTphrase);
        translationService = initLanguageTranslatorService();
        speechService = initTextToSpeechService();


        //view model
        //noinspection deprecation
        LanguageViewModel languageViewModel = ViewModelProviders.of(this).get(LanguageViewModel.class);
        languageViewModel.getAllSubscribedLanguages().observe(this, languages -> {
            for (Language lang : languages
            ) {
                String id = lang.getLangId();
                String name = lang.getLanguage();
//                    Log.i("lang", "onChanged: "+id);
                if (lang.getCheckValue() == 1) {
                    langIdList.add(id);
                    langNameList.add(name);

                }

            }

            //dropdown list for subscribed languages
            langSpinner = findViewById(R.id.langSpinner);
            ArrayAdapter spinnerAdapter = new ArrayAdapter(TranslateActivity.this, android.R.layout.simple_spinner_item, langNameList.toArray());
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            langSpinner.setAdapter(spinnerAdapter);
        });


    }

    private LanguageTranslator initLanguageTranslatorService() {
        IamAuthenticator authenticator = new IamAuthenticator(getString(R.string.langTranslatorAPIKey));
        LanguageTranslator service = new LanguageTranslator(getString(R.string.cloudVersion), authenticator);
        service.setServiceUrl(getString(R.string.language_translator_url));
        return service;
    }


    private static class TranslationTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            TranslateOptions translateOptions = new
                    TranslateOptions.Builder()
                    .addText(params[0])
                    .source(com.ibm.watson.language_translator.v3.util.Language.ENGLISH)
                    .target(langSelected)
                    .build();

            try {
                result = translationService.translate(translateOptions).execute().getResult();
            } catch (com.ibm.cloud.sdk.core.service.exception.NotFoundException nfe) {
                return "conversion service for this language is not available.";
            } catch (com.ibm.cloud.sdk.core.service.exception.BadRequestException bre) {
                return "Cannot convert phrases to the same language";
            }

            return result.getTranslations().get(0).getTranslation();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            translatedPhraseView.setText(s);
        }
    }

    public void getSavedPhrases(View view) {
        Intent intent = new Intent(TranslateActivity.this, GetSavedPhrase.class);
        startActivityForResult(intent, PHRASE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHRASE_REQUEST && resultCode == RESULT_OK && !data.getStringExtra(EXTRA_PHRASE).equalsIgnoreCase("")) {
            String phrase = data.getStringExtra(EXTRA_PHRASE);
            phraseBox.setText(phrase);
        }
    }

    /**
     * Function executed when the translate button is clicked
     * @param view
     */
    public void translatePhrase(View view) {
        int position = langSpinner.getSelectedItemPosition();
        langSelected = langIdList.get(position);
        String text = phraseBox.getText().toString();
        new TranslationTask().execute(text);

        Snackbar.make(
                view,
                "Translating .....",
                Snackbar.LENGTH_LONG

        ).show();
    }

    //text to speech service
    private TextToSpeech initTextToSpeechService() {
        Authenticator authenticator = new
                IamAuthenticator(getString(R.string.textToSpeechAPIkey));
        TextToSpeech service = new com.ibm.watson.text_to_speech.v1.TextToSpeech(authenticator);
        service.setServiceUrl(getString(R.string.textToSpeechURL));
        return service;
    }

    @SuppressLint("StaticFieldLeak")
    private class SynthesisTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            SynthesizeOptions synthesizeOptions = new
                    SynthesizeOptions.Builder()
                    .text(params[0])
                    .voice(SynthesizeOptions.Voice.EN_US_LISAVOICE)
                    .accept(HttpMediaType.AUDIO_WAV)
                    .build();
            player.playStream(speechService.synthesize(synthesizeOptions).execute()
                    .getResult());
            return getString(R.string.did_synthesize);
        }
    }

    /**
     * function executed when the pronounce button is executed
     * @param view
     */
    public void getPronunciation(View view) {
        String text = translatedPhraseView.getText().toString();
        new SynthesisTask().execute(text);

        Snackbar.make(
                view,
                "Please wait a moment ......",
                Snackbar.LENGTH_LONG

        ).show();
    }
}
