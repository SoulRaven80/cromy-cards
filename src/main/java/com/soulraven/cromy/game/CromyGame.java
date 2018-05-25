package com.soulraven.cromy.game;

import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import com.soulraven.cromy.model.CromyStat;

public class CromyGame {
    static Board board = new Board();

    public static void main(String[] args) {
        board.initBoard(CromyDeck.getCards());
        gameLoop();
    }

    static void gameLoop() {
        while (board.p1HasCards() && board.p2HasCards()) { // Main Game Loop
            CromyCard p1Draw = board.drawFromP1();
            CromyCard p2Draw = board.drawFromP2();
            System.out.println("p1 draws " + p1Draw.toString());
            System.out.println("p2 draws " + p2Draw.toString());
            int result = compareDraw(p1Draw, p2Draw, false);
            if (result == 0) {
                board.addToBounty(p1Draw); // The two tied cards are added to the bounty.
                board.addToBounty(p2Draw);
                if (warWasDeclared() == 1) {
                    board.claimBountyRewardP1();
                } else {
                    board.claimBountyRewardP2();
                }
            }
            checkGameOver();
        }
    }

    static int warWasDeclared() { // Returns the winner of the war as an integer.
        checkGameOver(); // We have to check for shuffle before every draw.
        CromyCard p1Draw = board.drawFromP1(); // Pop cards
        CromyCard p2Draw = board.drawFromP2();
        System.out.println("p1 draws " + p1Draw.toString());
        System.out.println("p2 draws " + p2Draw.toString());
        board.addToBounty(p1Draw);
        board.addToBounty(p2Draw);
        int result = compareDraw(p1Draw, p2Draw, true);
        if (result == 1) {
            System.out.println("p1 wins the war!");
            return 1;
        }
        if (result == 2) { // Compare to opponent's card
            System.out.println("p2 wins the war!");
            return 2;
        } else {
            System.out.println("It's another level of war!");
            return warWasDeclared(); // recursive call
        }
    }

    // TODO only random for now
    static int compareDraw(CromyCard p1Draw, CromyCard p2Draw, boolean isWar) {
        CromyStat.StatName name = getRandomStat();
        System.out.println("Playing with " + name);
        if (p1Draw.getStat(name).compareTo(p2Draw.getStat(name)) > 0) { // Compare to opponent's card
            System.out.println("p1 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP1(p1Draw, p2Draw);
            }
            return 1;
        } else if (p2Draw.getStat(name).compareTo(p1Draw.getStat(name)) > 0) { // Compare to opponent's card
            System.out.println("p2 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP2(p1Draw, p2Draw);
            }
            return 2;
        } else {
            return 0;
        }
    }

    static void checkGameOver() {
        if (!board.p1HasCards()) { // Checks for shuffles.
            System.out.println("Player 1 is drawn out.");
            System.out.println("Player 2 wins!");
            System.exit(0);
        }
        if (!board.p2HasCards()) { // Checks for shuffles.
            System.out.println("Player 2 is drawn out.");
            System.out.println("Player 1 wins!");
            System.exit(0);
        }
    }

    private static CromyStat.StatName getRandomStat() {
        int value = (int) (Math.random() * 5);
        switch (value) {
            case 0: return CromyStat.StatName.HEIGHT;
            case 1: return CromyStat.StatName.WEIGHT;
            case 2: return CromyStat.StatName.STRENGTH;
            case 3: return CromyStat.StatName.SPEED;
            default: return CromyStat.StatName.TRANSFORMATION_SPEED;
        }
    }
}
