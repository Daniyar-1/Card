package com.example.card.domain;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game<Content> {

    private List<Card<Content>> cards = new ArrayList<>();
    private boolean isFinish = false;

    public Game(List<Content> contents) {
        for (int i = 0; i < contents.size(); i++) {
            cards.add(new Card<>(i * 2, false, false, contents.get(i)));
            cards.add(new Card<>((i * 2) + 1, false, false, contents.get(i)));
            Collections.shuffle(cards);
        }
    }

    public void choose(Card<Content> card) {
        card.setFaceUp(!card.isFaceUp());
        if (card.isFaceUp())
        checkPairs(card);
    finishGame();
    }

    private void checkPairs(Card<Content> openedCard) {
        for (Card<Content> anotherCard : cards) {
            if (openedCard.isFaceUp() && anotherCard.isFaceUp()) {
                if (openedCard.equals(anotherCard) && openedCard.getId() != anotherCard.getId()) {
                    openedCard.setMatched(true);
                    anotherCard.setMatched(true);
                    Log.e("TAG", "Match!");
                } else if (!openedCard.equals(anotherCard)) {
                    openedCard.setFaceUp(false);
                    anotherCard.setFaceUp(false);
                }
            }
        }
        remove();
    }

    private void remove() {
        List<Card<Content>> resultCards = new ArrayList<>(cards);
        for (Card<Content> c : cards) {
            if (c.isMatched()) {
                resultCards.remove(c);
            }
        }
        cards.clear();
        cards.addAll(resultCards);

    }

    public List<Card<Content>> getCards() {
        return cards;
    }

    public boolean finished() {
        return isFinish;
    }

    public void setFinish(boolean isFinish) {
        this.isFinish = isFinish;
    }

    private void finishGame() {
        if (cards.isEmpty())
            setFinish(true);
    }
}
