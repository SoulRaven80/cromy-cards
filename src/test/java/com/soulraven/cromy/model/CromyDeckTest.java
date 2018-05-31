package com.soulraven.cromy.model;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CromyDeckTest {

    @Test
    public void testGetCardsSize() {
        List<CromyCard> cards = CromyDeck.getCards();
        assertEquals("Unexpected list size", 32, cards.size());
    }

    @Test
    public void testGetCardsIds() {
        List<CromyCard> cards = CromyDeck.getCards();
        Collections.sort(cards, new Comparator<CromyCard>() {
            @Override
            public int compare(CromyCard card1, CromyCard card2) {
                return card1.getId().compareTo(card2.getId());
            }
        });
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

    @Test
    public void testGetCard() {
        String cardId = "A1";
        CromyCard a1 = CromyDeck.getCard(cardId);

        assertNotNull("Expected object not found", a1);
        assertEquals("Unexpected card Id", cardId, a1.getId());
        assertEquals("Unexpected card name", "Smokescreen", a1.getName());
    }
}