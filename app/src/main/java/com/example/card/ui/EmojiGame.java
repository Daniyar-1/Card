package com.example.card.ui;

import android.app.AlertDialog;
import android.content.Context;

import com.example.card.R;
import com.example.card.domain.Card;
import com.example.card.domain.Game;

import java.util.List;

public class EmojiGame {

    private Game<String> game;
    private Context context;

    public EmojiGame(Context context) {
        this.context = context;
        game = new Game<>(List.of("\uD83D\uDE02", "\uD83D\uDE0E", "\uD83C\uDF1A", "\uD83D\uDE18", "\uD83D\uDE48", "\uD83D\uDE0D"));
    }

    public EmojiGame() {

    }

    public void choose(Card<String> card) {
        game.choose(card);
        if (game.finished())
            msgWin();
    }

    private void msgWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(R.layout.won_msg).show();
    }


    public List<Card<String>> getCards() {
        return game.getCards();
    }
}
