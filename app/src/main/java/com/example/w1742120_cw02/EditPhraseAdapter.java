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

    Phrase selectedPhrase;
    RadioButton rb;
    @Override
    public void onBindViewHolder(@NonNull EditPhraseHolder holder, int position) {
        holder.phraseDescrip.setText(phrases.get(position).getDescription());

        //implement a click listener on the radio button from the viewholder
        //once clicked current object should be recorded (instance variable)
        //once you click it i have to record two things - the radio button, item


        holder.radioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rb != null){
                    rb.setChecked(false);
                    rb = holder.radioBtn;
                    selectedPhrase = phrases.get(position);
                }else {
                    rb = holder.radioBtn;
                    rb.setChecked(true);
                    selectedPhrase = phrases.get(position);
                }

                notifyDataSetChanged();
            }
        });

        for (int i=0; i<phrases.size(); i++){
            if (phrases.get(position) == selectedPhrase){
                holder.radioBtn.setChecked(true);
            }else
                holder.radioBtn.setChecked(false);

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
        private RadioButton radioBtn;//radio button


        public EditPhraseHolder(@NonNull View itemView) {
            super(itemView);
            phraseDescrip = itemView.findViewById(R.id.txt_rbview_phrase);
            radioBtn = itemView.findViewById(R.id.radio_btn);
        }
    }
}
