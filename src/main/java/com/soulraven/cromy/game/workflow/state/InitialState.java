package com.soulraven.cromy.game.workflow.state;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.model.CromyCard;
import lombok.Builder;

public class InitialState extends State {

    public InitialState(Board board) {
        super(board,"Initial", new ComparisonState(board));
    }


    @Override
    public void execute() {
        CromyCard p1Draw = board.drawFromP1();
    }
}
