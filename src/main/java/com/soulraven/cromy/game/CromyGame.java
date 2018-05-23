package com.soulraven.cromy.game;

import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import com.soulraven.cromy.model.CromyStat;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class CromyGame {
    static Stack<CromyCard> p1Deck = new Stack<>();
    static Stack<CromyCard> p2Deck = new Stack<>();
    private static LinkedList<CromyCard> p1Graveyard = new LinkedList<>();
    private static LinkedList<CromyCard> p2Graveyard = new LinkedList<>();
    private static Stack<CromyCard> warBounty = new Stack<>();

    public static void main(String[] args) {
        List<CromyCard> cards = CromyDeck.getCards();

        Stack<CromyCard> shuffled = shuffle(cards); // Perform the initial shuffling.
        for (int i = 0; i < shuffled.size()-1; i++) {
            // Pop 26 off
            p1Deck.push(shuffled.pop());
            // Pop 26 off
            p2Deck.push(shuffled.pop());
            i++;
        }
        // Setup done.
        gameLoop();
    }

    static void gameLoop() {
        while (p1Deck.size() + p1Graveyard.size() > 0 && p2Deck.size() + p2Graveyard.size() > 0) { // Main Game Loop
            CromyCard p1Draw = p1Deck.pop(); // Pop cards
            CromyCard p2Draw = p2Deck.pop();
            System.out.println("p1 draws " + p1Draw.toString());
            System.out.println("p2 draws " + p2Draw.toString());
            int result = compareDraw(p1Draw, p2Draw, false);
            if (result == 0) {
                warBounty.push(p1Draw); // The two tied cards are added to the bounty.
                warBounty.push(p2Draw);
                unlessOfCourse(); // War were declared. https://www.youtube.com/watch?v=TS3kiRYcDAo
            }
            checkForShuffleOrEnd(); // Check for shuffle before every new round of draws.
        }
    }

    static void unlessOfCourse() { // Triggers a possibly recursive war and handles result.
        if (warWereDeclared() == 1) {
            while (!warBounty.empty()) {
                CromyCard c = warBounty.pop();
                p1Graveyard.push(c);
                System.out.println("p1 wins " + c.toString()); // Not the game, he wins the specified card.
            }
        } else {
            // p2 wins
            while (!warBounty.empty()) {
                CromyCard c = warBounty.pop();
                p2Graveyard.push(c);
                System.out.println("p2 wins " + c.toString()); // Not the game, he wins the specified card.
            }
        }
    }

    static int warWereDeclared() { // Returns the winner of the war as an integer.
        checkForShuffleOrEnd(); // We have to check for shuffle before every draw.
        warBounty.push(p1Deck.pop()); // Amasses the bounty.
        warBounty.push(p2Deck.pop());
        System.out.println("Each player adds a card in bounty.");
        checkForShuffleOrEnd(); // We have to check for shuffle before every draw.
        CromyCard p1Draw = p1Deck.pop(); // Pop cards
        CromyCard p2Draw = p2Deck.pop();
        System.out.println("p1 draws " + p1Draw.toString());
        System.out.println("p2 draws " + p2Draw.toString());
        warBounty.push(p1Draw); // Takes from loser or grants to winner.
        warBounty.push(p2Draw);
        int result = compareDraw(p1Draw, p2Draw, true);
        if (result == 1) { // Compare to opponent's card
            System.out.println("p1 wins the war!");
            return 1;
        }
        if (result == 2) { // Compare to opponent's card
            System.out.println("p2 wins the war!");
            return 2;
        } else {
            System.out.println("It's another level of war!");
            return warWereDeclared(); // recursive call
        }
    }

    // TODO only weight 4 now
    static int compareDraw(CromyCard p1Draw, CromyCard p2Draw, boolean isWar) {
        CromyStat.StatName name = CromyStat.StatName.WEIGHT;
        if (p1Draw.getStat(name).getValue() == 1 && p2Draw.getStat(name).getValue() == 14) {
            System.out.println("p1 takes down an Ace!");
            if (!isWar) {
                p1Graveyard.add(p1Draw); // Takes from loser or grants to winner.
                p1Graveyard.add(p2Draw);
            }
            return 1;
        } else if (p2Draw.getStat(name).getValue() == 1 && p1Draw.getStat(name).getValue() == 14) {
            System.out.println("p2 takes down an Ace!");
            if (!isWar) {
                p2Graveyard.add(p1Draw); // Takes from loser or grants to winner.
                p2Graveyard.add(p2Draw);
            }
            return 2;
        } else if (p1Draw.getStat(name).getValue() > p2Draw.getStat(name).getValue()) { // Compare to opponent's card, account for Ace Takedown in joker games.
            System.out.println("p1 wins the draw!");
            if (!isWar) {
                p1Graveyard.add(p1Draw); // Takes from loser or grants to winner.
                p1Graveyard.add(p2Draw);
            }
            return 1;
        } else if (p2Draw.getStat(name).getValue() > p1Draw.getStat(name).getValue()) { // Compare to opponent's card
            System.out.println("p2 wins the draw!");
            if (!isWar) {
                p2Graveyard.add(p1Draw); // Takes from loser or grants to winner.
                p2Graveyard.add(p2Draw);
            }
            return 2;
        } else
            return 0;
    }

    static void checkForShuffleOrEnd() {
        if (p1Deck.size() < 1) { // Checks for shuffles.
            System.out.println("Player 1 is drawn out.");
            if (p1Graveyard.size() < 1) { // Checks for game end.
                System.out.println("Player 2 wins!");
                System.exit(0);
            } else {
                p1Deck = shuffle(p1Graveyard);
                p1Graveyard = new LinkedList<CromyCard>();
            }
        }
        if (p2Deck.size() < 1) { // Checks for shuffles.
            System.out.println("Player 2 is drawn out.");
            if (p2Graveyard.size() < 1) { // Checks for game end.
                System.out.println("Player 1 wins!");
                System.exit(0);
            } else {
                p2Deck = shuffle(p2Graveyard);
                p2Graveyard = new LinkedList<CromyCard>();
            }
        }
    }

    static Stack<CromyCard> shuffle(List<CromyCard> graveyard) {
        System.out.println("Pausing to shuffle " + graveyard.size() + " cards...");
        Stack<CromyCard> shuffled = new Stack<>();
        Random RNG = new Random();
        while (graveyard.size() > 0) {
            // select a card
            int index = RNG.nextInt(graveyard.size());
            CromyCard selected = graveyard.remove(index);
            shuffled.push(selected);
        }
        return shuffled;
    }
}
