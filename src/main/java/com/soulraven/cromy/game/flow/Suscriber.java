package com.soulraven.cromy.game.flow;

import com.soulraven.cromy.game.Player;
import com.soulraven.cromy.model.CromyCard;

public interface Suscriber {

    void draw(Player player, CromyCard card);

    void drawWinner(Player winner);

    void gameOver(Player loser);
}
