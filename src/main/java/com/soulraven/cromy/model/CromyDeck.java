package com.soulraven.cromy.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CromyDeck {
/*
name = Astrotrain
stats.HEIGHT = 4.4
stats.WEIGHT = 10
stats.STRENGTH = 500
stats.SPEED = 2900
stats.TRANSFORMATION_SPEED = 1
*/

    private static List<CromyCard> cards;

    public static List<CromyCard> getCards() {
        cards = new ArrayList<>();

        List<CromyStat> stats = new ArrayList<>();
        stats.add(CromyStat.builder().statName(CromyStat.StatName.HEIGHT).value(4.4F).build());
        stats.add(CromyStat.builder().statName(CromyStat.StatName.WEIGHT).value(10F).build());
        stats.add(CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(500F).build());
        stats.add(CromyStat.builder().statName(CromyStat.StatName.SPEED).value(2900F).build());
        stats.add(CromyStat.builder().statName(CromyStat.StatName.TRANSFORMATION_SPEED).value(1F).build());

        cards.add(CromyCard.builder().name("Astrotrain").stats(stats).build());

        buildRamdomDeck();

        return cards;
    }

    private static void buildRamdomDeck() {

        for (int i=0; i<40; i++) {
            List<CromyStat> stats = new ArrayList<>();

            stats.add(CromyStat.builder().statName(CromyStat.StatName.HEIGHT).value((Math.random() + 1) * 10).build());
            stats.add(CromyStat.builder().statName(CromyStat.StatName.WEIGHT).value((Math.random() + 1) * 100).build());
            stats.add(CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value((Math.random() + 1) * 1000).build());
            stats.add(CromyStat.builder().statName(CromyStat.StatName.SPEED).value((Math.random() + 1) * 10000).build());
            stats.add(CromyStat.builder().statName(CromyStat.StatName.TRANSFORMATION_SPEED).value(Math.random()* 6).build());

            cards.add(CromyCard.builder().name("Card # " + i).stats(stats).build());
        }
    }
}
