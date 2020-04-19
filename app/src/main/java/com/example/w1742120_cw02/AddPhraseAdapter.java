package com.example.w1742120_cw02;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AddPhraseAdapter extends RecyclerView.Adapter<AddPhraseAdapter.PhraseHolder> {
    private List<Phrase> phrases = new ArrayList<>();
    private OnCardClickListener listener;


    //inflate the view holder
    @NonNull
    @Override
    public PhraseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.phrase_item, parent, false);
        return new PhraseHolder(itemView);
    }

    //set holder with phrase
    @Override
    public void onBindViewHolder(@NonNull PhraseHolder holder, int position){
        Phrase currentPhrase = phrases.get(position);
        holder.textViewPhrase.setText(currentPhrase.getDescription());
    }

    //get total number of phrases
    @Override
    public int getItemCount() {
        return phrases.size();
    }

    //set phrases
    public void setPhrases(List<Phrase> phrases){
        this.phrases = phrases;
        notifyDataSetChanged();
    }


    class PhraseHolder extends RecyclerView.ViewHolder{
        private TextView textViewPhrase;

        public PhraseHolder(@NonNull View itemView) {
            super(itemView);
            textViewPhrase = itemView.findViewById(R.id.txt_view_phrase);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onCardClick(phrases.get(position));
                        Log.i("phrasevalue", "Phrase val : "+phrases.get(position));
                    }
                }
            });

        }

    }

    public interface OnCardClickListener{
        void onCardClick(Phrase phrase);
    }

    public void setOnCardClickListener(OnCardClickListener listener){
        this.listener = listener;
    }

}
