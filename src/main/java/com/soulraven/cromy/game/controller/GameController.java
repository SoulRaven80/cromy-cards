package com.soulraven.cromy.game.controller;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameController {

    @Autowired
    private Board board;

    @GetMapping("/board")
    public String greeting(Model model) {
        model.addAttribute("card", board.drawFromP1());
        return "board";
    }

    @RequestMapping("/drawP1")
    public CromyCard drawP1() {
        return board.drawFromP1();
    }
}
