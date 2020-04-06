package com.example.w1742120_cw02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditPhraseAdapter extends RecyclerView.Adapter<EditPhraseAdapter.EditPhraseHolder> {

    private List<Phrase> phrases = new ArrayList<>();
    private RadioGroup lastCheckedRadioGroup = null;
    private Context context;

    public EditPhraseAdapter(Context ctx) {
        context = ctx;
    }

    @NonNull
    @Override
    public EditPhraseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phrase_item_radiobtn, parent, false);
        return new EditPhraseHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EditPhraseHolder holder, int position) {
        Phrase currentPhrase = phrases.get(position);
        holder.phraseDescrip.setText(currentPhrase.getDescription());

        int id = (position+1)*100;
        for (Phrase phrase : phrases){
            RadioButton rb = new RadioButton(EditPhraseAdapter.this.context);
            rb.setId(id++);

            holder.radioGroup.addView(rb);
        }
    }

    @Override
    public int getItemCount() {
        return phrases.size();
    }

    public void setPhrases(List<Phrase> phrases){
        this.phrases = phrases;
        notifyDataSetChanged();
    }

    class EditPhraseHolder extends RecyclerView.ViewHolder{
        private TextView phraseDescrip;
        private RadioGroup radioGroup;


        public EditPhraseHolder(@NonNull View itemView) {
            super(itemView);
            phraseDescrip = itemView.findViewById(R.id.txt_rbview_phrase);
            radioGroup = itemView.findViewById(R.id.radio_grp);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    //since only one phrase is allowed to be selected this logic clears the previous selection
                    //it checks the state of the last radioGroup and clears it if it meets the conditions

                    if (lastCheckedRadioGroup != null
                    && lastCheckedRadioGroup.getCheckedRadioButtonId() != radioGroup.getCheckedRadioButtonId()
                    && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1){

                        lastCheckedRadioGroup.clearCheck();

                        Toast.makeText(EditPhraseAdapter.this.context,
                                "Radio button clicked " + radioGroup.getCheckedRadioButtonId(),
                                Toast.LENGTH_SHORT).show();
                    }
                    lastCheckedRadioGroup = radioGroup;
                }
            });
        }
    }
}
