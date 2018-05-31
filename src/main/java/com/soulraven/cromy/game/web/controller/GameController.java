package com.soulraven.cromy.game.web.controller;

import com.soulraven.cromy.game.Board;
import com.soulraven.cromy.game.Player;
import com.soulraven.cromy.game.form.GameForm;
import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import com.soulraven.cromy.model.CromySkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GameController {

    @Autowired
    private Board board;

    @GetMapping("/startNewGame")
    public String startNewGame(Model model) {
        board.init();
        return board(model);
    }

    @GetMapping("/board")
    public String board(Model model) {
        if (checkGameOver(board.getP1())) {
            model.addAttribute("winner", board.getP2());
        }
        else if (checkGameOver(board.getP2())) {
            model.addAttribute("winner", board.getP1());
        }
        else {
            CromyCard card = board.drawFromP1();

            model.addAttribute("card", card);
            GameForm gameForm = new GameForm();
            gameForm.setCardId(card.getId());
            setCommonAttributes(model, gameForm);
            return "board";
        }
        return "gameOver";
    }

    @GetMapping("/tied")
    public String tied(@ModelAttribute("gameForm") GameForm gameForm, Model model) {
        if (checkGameOver(board.getP1())) {
            model.addAttribute("winner", board.getP1());
        }
        else if (checkGameOver(board.getP2())) {
            model.addAttribute("winner", board.getP2());
        }
        else {
            CromyCard p1Draw = board.drawFromP1();
            CromyCard p2Draw = board.drawFromP2();
            CromySkill skill = p1Draw.getSkill(gameForm.getSkillName());

            int result = compareDraw(p1Draw, p2Draw, skill.getSkillName(), true);

            addDrawsAttributes(model, p1Draw, p2Draw, skill);

            setCommonAttributes(model, gameForm);
            if (result > 0) {
                model.addAttribute("result", result);
                return "bothDrawn";
            }
            return "tied";
        }
        return "gameOver";
    }

    @PostMapping("/selectCard")
    public String selectCard(@ModelAttribute("gameForm") GameForm gameForm, Model model) {
        CromyCard p1Draw = CromyDeck.getCard(gameForm.getCardId());
        CromyCard p2Draw = board.drawFromP2();
        CromySkill skill = p1Draw.getSkill(gameForm.getSkillName());

        int result = compareDraw(p1Draw, p2Draw, skill.getSkillName(), false);

        addDrawsAttributes(model, p1Draw, p2Draw, skill);

        setCommonAttributes(model, gameForm);
        if (result > 0) {
            model.addAttribute("result", result);
            return "bothDrawn";
        }
        return "tied";
    }

    private void setCommonAttributes(Model model, GameForm gameForm) {
        model.addAttribute("p1DeckSize", board.deckSize(board.getP1()));
        model.addAttribute("p2DeckSize", board.deckSize(board.getP2()));
        model.addAttribute("p1Number", board.getP1().getNumber());
        model.addAttribute("p2Number", board.getP2().getNumber());
        model.addAttribute("gameForm", gameForm);
    }

    private void addDrawsAttributes(Model model, CromyCard p1Draw, CromyCard p2Draw, CromySkill skill) {
        model.addAttribute("p1Draw", p1Draw);
        model.addAttribute("skill", skill);
        model.addAttribute("p2Draw", p2Draw);
    }

    private int compareDraw(CromyCard p1Draw, CromyCard p2Draw, CromySkill.SkillName skillName, boolean isWar) {
        System.out.println("Playing with " + skillName);
        if (p1Draw.getSkill(skillName).compareTo(p2Draw.getSkill(skillName)) > 0) {
            System.out.println("p1 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP1(p1Draw, p2Draw);
            }
            return 1;
        } else if (p2Draw.getSkill(skillName).compareTo(p1Draw.getSkill(skillName)) > 0) {
            System.out.println("p2 wins the draw!");
            if (!isWar) {
                board.claimRoundRewardP2(p1Draw, p2Draw);
            }
            return 2;
        } else {
            return 0;
        }
    }

    private boolean checkGameOver(Player player) {
        return player.getCards().isEmpty();
    }
}
