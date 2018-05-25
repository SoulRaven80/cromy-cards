package com.soulraven.cromy.game.workflow.state;

import com.soulraven.cromy.game.Board;
import lombok.Builder;

@Builder
public class ComparisonState extends State {

    public ComparisonState(Board board) {
        super(board, "Comparison", null);
    }

    @Override
    public void execute() {

    }
}
