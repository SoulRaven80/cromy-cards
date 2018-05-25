package com.soulraven.cromy.game.workflow;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.game.workflow.state.ComparisonState;
import com.soulraven.cromy.game.workflow.state.InitialState;
import com.soulraven.cromy.game.workflow.state.State;
import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class GameWorkflow {

    @Autowired
    private Board board;
    private List<State> states;

    public GameWorkflow() {
        this.states = new ArrayList<>();
        states.add(new InitialState(board));
//        states.add(new ComparisonState(board));
    }

    public void run() throws IOException {
        while (board.p1HasCards() && board.p2HasCards()) { // Main Game Loop
            states.get(0).execute();

            CromyCard p1Draw = board.drawFromP1();
            System.out.println("p1 draws " + p1Draw.toString());

            System.out.println("Select skill:");
            System.out.println("1: Height");
            System.out.println("2: Weight");
            System.out.println("3: Strength");
            System.out.println("4: Speed");
            System.out.println("5: Transformation Speed");
            Scanner scanner = new Scanner(System.in);
            int read = scanner.nextInt();

            CromyStat.StatName statName = getStat(read);

            CromyCard p2Draw = board.drawFromP2();
            System.out.println("p2 draws " + p2Draw.toString());
            int result = compareDraw(p1Draw, p2Draw, statName, false);
            if (result == 0) {
                board.addToBounty(p1Draw); // The two tied cards are added to the bounty.
                board.addToBounty(p2Draw);
                if (warWasDeclared(statName) == 1) {
                    board.claimBountyRewardP1();
                } else {
                    board.claimBountyRewardP2();
                }
            }
            checkGameOver();
        }
    }

    static int warWasDeclared(CromyStat.StatName statName) { // Returns the winner of the war as an integer.
        checkGameOver(); // We have to check for shuffle before every draw.
        CromyCard p1Draw = board.drawFromP1(); // Pop cards
        CromyCard p2Draw = board.drawFromP2();
        System.out.println("p1 draws " + p1Draw.toString());
        System.out.println("p2 draws " + p2Draw.toString());
        board.addToBounty(p1Draw);
        board.addToBounty(p2Draw);
        int result = compareDraw(p1Draw, p2Draw, statName, true);
        if (result == 1) {
            System.out.println("p1 wins the war!");
            return 1;
        }
        if (result == 2) { // Compare to opponent's card
            System.out.println("p2 wins the war!");
            return 2;
        } else {
            System.out.println("It's another level of war!");
            return warWasDeclared(statName); // recursive call
        }
    }

    static int compareDraw(CromyCard p1Draw, CromyCard p2Draw, CromyStat.StatName statName, boolean isWar) {
        System.out.println("Playing with " + statName);
        if (p1Draw.getStat(statName).compareTo(p2Draw.getStat(statName)) > 0) { // Compare to opponent's card
            System.out.println("p1 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP1(p1Draw, p2Draw);
            }
            return 1;
        } else if (p2Draw.getStat(statName).compareTo(p1Draw.getStat(statName)) > 0) { // Compare to opponent's card
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

    private static CromyStat.StatName getStat(int number) {
        switch (number) {
            case 1: return CromyStat.StatName.HEIGHT;
            case 2: return CromyStat.StatName.WEIGHT;
            case 3: return CromyStat.StatName.STRENGTH;
            case 4: return CromyStat.StatName.SPEED;
            default: return CromyStat.StatName.TRANSFORMATION_SPEED;
        }
    }
}
