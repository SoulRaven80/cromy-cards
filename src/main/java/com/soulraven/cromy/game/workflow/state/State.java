package com.soulraven.cromy.game.workflow.state;

import com.soulraven.cromy.game.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class State {

    protected Board board;
    protected String name;
    protected State next;

    public abstract void execute();
}
