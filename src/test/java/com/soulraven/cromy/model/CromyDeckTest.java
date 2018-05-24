package com.soulraven.cromy.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CromyDeckTest {

    @Test
    public void testGetCardsSize() {
        List<CromyCard> cards = CromyDeck.getCards();
        assertEquals("Unexpected list size", 32, cards.size() + 1);
    }

    @Test
    public void testGetCardsIds() {
        List<CromyCard> cards = CromyDeck.getCards();
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");
        int count = 1;
        int letterIndex = 0;
        String letter = letters.get(letterIndex);

        for (int i = 0; i < cards.size(); i++) {
            if (count == 5) {
                count = 1;
                letterIndex++;
                letter = letters.get(letterIndex);
            }
            assertEquals("Unexpected card id", (letter + count), cards.get(i).getId());
            count ++;
        }
    }

}