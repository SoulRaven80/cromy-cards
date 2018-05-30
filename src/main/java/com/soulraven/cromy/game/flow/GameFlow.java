package com.soulraven.cromy.game.flow;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.game.Player;
import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromySkill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class GameFlow {

    private List<Suscriber> suscribers;
    private Board board;
    private Player winner;
    private Player loser;

    public GameFlow() {
        board = new Board();
        winner = board.getP1();
        loser = board.getP2();
        suscribers = new ArrayList<>();
    }

    public void addSuscriber(Suscriber suscriber) {
        suscribers.add(suscriber);
    }

    public void runGame() {
        // step1() // checkGameOver(p1)
        // step2() // checkGameOver(p2)
        // step3() // draw(p1)
        // step4() // selectSkill(p1)
        // step5() // draw(p2)
        // step6() // validate
        // step6_1() // winner(p1)
        // step6_2() // winner(p2)
        // step6_3() // tied -> step1() through step6() // skipping step4()
        // step7() // step1()
    }

    public void run() {
        while (!checkGameOver(winner) && !checkGameOver(loser)) {
            CromyCard winnerDraw = draw(winner);
            CromySkill.SkillName skillName = selectSkill(readSkillSelection());
            CromyCard loserDraw = draw(loser);
            resolveDraw(winner, winnerDraw, loser, loserDraw, skillName);
        }
    }

    private boolean checkGameOver(Player player) {
        boolean empty = player.getCards().isEmpty();
        if (empty) {
            notifyGameOver(player);
        }
        return empty;
    }

    public CromyCard stepOne(Player player) {
        return draw(player);
    }

    public CromyCard draw(Player player) {
        CromyCard card = player.draw();
        notifyDraw(player, card);
        board.addToBounty(card);
        return card;
    }

    public CromySkill.SkillName selectSkill(String skillName) {
        return CromySkill.SkillName.valueOf(skillName);
    }

    public CromySkill.SkillName selectSkill(int skillNumber) {
        switch (skillNumber) {
            case 1:
                return CromySkill.SkillName.HEIGHT;
            case 2:
                return CromySkill.SkillName.WEIGHT;
            case 3:
                return CromySkill.SkillName.STRENGTH;
            case 4:
                return CromySkill.SkillName.SPEED;
            default:
                return CromySkill.SkillName.TRANSFORMATION_SPEED;
        }
    }

    public Player resolveDraw(Player p1, CromyCard draw1, Player p2, CromyCard draw2, CromySkill.SkillName skillName) {
        // 1- p1.draw >> p2.draw
        if (draw1.getSkill(skillName).compareTo(draw2.getSkill(skillName)) > 0) {
            winner = p1;
            loser = p2;
        }
        // 2- p2.draw >> p1.draw
        else if (draw1.getSkill(skillName).compareTo(draw2.getSkill(skillName)) < 0) {
            winner = p2;
            loser = p1;
        } else {
            if (!checkGameOver(p1) && !checkGameOver(p2)) {
                resolveDraw(p1, draw(p1), p2, draw(p2), skillName);
            }
        }
        notifyWinner(winner);
        board.claimBountyReward(winner);
        return winner;
    }

    // TODO move logic
    private int readSkillSelection() {
        System.out.println("Select skill:");
        System.out.println("1: Height");
        System.out.println("2: Weight");
        System.out.println("3: Strength");
        System.out.println("4: Speed");
        System.out.println("5: Transformation Speed");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void notifyDraw(Player player, CromyCard card) {
        for (int i = 0; i < suscribers.size(); i++) {
            suscribers.get(i).draw(player, card);
        }
    }

    private void notifyWinner(Player winner) {
        for (int i = 0; i < suscribers.size(); i++) {
            suscribers.get(i).drawWinner(winner);
        }
    }

    private void notifyGameOver(Player loser) {
        for (int i = 0; i < suscribers.size(); i++) {
            suscribers.get(i).gameOver(loser);
        }
    }
}
