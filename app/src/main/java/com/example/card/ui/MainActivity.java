package com.example.card.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.card.R;
import com.example.card.domain.Card;
import com.example.card.ui.adapter.EmojiAdapter;

public class MainActivity extends AppCompatActivity implements EmojiAdapter.Listener {

    private RecyclerView recyclerView;
    private EmojiAdapter emojiAdapter;
    private EmojiGame emojiGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        emojiAdapter = new EmojiAdapter( new EmojiGame(this),this);
        recyclerView.setAdapter(emojiAdapter);
    }

    @Override
    public void cardClick(Card<String> card) {
        Log.e("TAG", String.valueOf(card.getId()));
        emojiAdapter.notifyDataSetChanged();
    }
}