package com.soulraven.cromy.game.console;

import com.soulraven.cromy.game.Player;
import com.soulraven.cromy.game.flow.Suscriber;
import com.soulraven.cromy.model.CromyCard;

public class ConsoleSuscriber implements Suscriber {

    @Override
    public void draw(Player player, CromyCard card) {
        System.out.println("Player " + player.getNumber() + " draws " + card.toString());
    }

    @Override
    public void drawWinner(Player winner) {
        System.out.println("Player " + winner.getNumber() + " wins the draw!");
    }

    @Override
    public void gameOver(Player loser) {
        System.out.println("Player " + loser.getNumber() + " is drawn out.");
        System.out.println("Player " + loser.getNumber() + " loses!");
        System.exit(0);
    }
}
