package com.example.card.ui.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.card.R;
import com.example.card.domain.Card;
import com.example.card.ui.EmojiGame;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.EmojiHolder> {

    private EmojiGame emojiGame;
    private Listener listener;


    public EmojiAdapter(EmojiGame emojiGame, Listener listener) {
        this.emojiGame = emojiGame;
        this.listener = listener;
    }

    public EmojiAdapter(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmojiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new EmojiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiHolder holder, int position) {
        holder.onBind(emojiGame.getCards().get(position));
    }

    @Override
    public int getItemCount() {
        return emojiGame.getCards().size();
    }

    public class EmojiHolder extends RecyclerView.ViewHolder {
        private TextView tvCard;

        public EmojiHolder(@NonNull View itemView) {
            super(itemView);
            tvCard = itemView.findViewById(R.id.card);
        }

        public void onBind(Card<String> card) {
            if (card.isFaceUp()) {
                tvCard.setBackgroundColor(Color.WHITE);
                tvCard.setText(card.getContent());
            } else {
                tvCard.setBackgroundResource(R.drawable.bg_cards);
                tvCard.setText("");


            }
            itemView.setOnClickListener(v -> {
                emojiGame.choose(card);
                listener.cardClick(card);
            });
        }
    }

    public interface Listener {
        void cardClick(Card<String> card);
    }
}
