package com.soulraven.cromy.game.controller;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.game.form.GameForm;
import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import com.soulraven.cromy.model.CromyStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameController {

    @Autowired
    private Board board;

    @GetMapping("/board")
    public String greeting(Model model, HttpServletRequest request) {
        CromyCard card = board.drawFromP1();
        model.addAttribute("card", card);
        model.addAttribute("gameForm", new GameForm());
        request.getSession().setAttribute("card", card);
        return "board";
    }

    @PostMapping("/selectCard")
    public String selectCard(@ModelAttribute("gameForm") GameForm form, Model model, HttpServletRequest request) {
        CromyCard p1Draw = (CromyCard) request.getSession().getAttribute("card");
        CromyStat stat = p1Draw.getStat(form.getStatName());

        CromyCard p2Draw = board.drawFromP2();

        int result = compareDraw(p1Draw, p2Draw, stat.getStatName(), false);

        return "board";
    }

    int compareDraw(CromyCard p1Draw, CromyCard p2Draw, CromyStat.StatName statName, boolean isWar) {
        System.out.println("Playing with " + statName);
        if (p1Draw.getStat(statName).compareTo(p2Draw.getStat(statName)) > 0) {
            System.out.println("p1 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP1(p1Draw, p2Draw);
            }
            return 1;
        } else if (p2Draw.getStat(statName).compareTo(p1Draw.getStat(statName)) > 0) {
            System.out.println("p2 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP2(p1Draw, p2Draw);
            }
            return 2;
        } else {
            return 0;
        }
    }

    @RequestMapping("/drawP1")
    public CromyCard drawP1() {
        return board.drawFromP1();
    }
}
