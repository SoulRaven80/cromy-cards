package com.soulraven.cromy.game.console;

import com.soulraven.cromy.game.flow.GameFlow;

public class ConsoleGame {

    public static void main(String[] args) {
        GameFlow gameFlow = new GameFlow();
        gameFlow.addSuscriber(new ConsoleSuscriber());
        gameFlow.run();
    }
}
