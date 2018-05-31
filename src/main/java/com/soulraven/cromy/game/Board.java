package com.soulraven.cromy.game;

import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Component
@Getter
public class Board {

    private Player p1;
    private Player p2;
    private Stack<CromyCard> warBounty;

    public Board() {
        init();
    }

    public void init() {
        warBounty = new Stack<>();
        p1 = Player.builder().number("1").cards(new ArrayList<>()).build();
        p2 = Player.builder().number("2").cards(new ArrayList<>()).build();
        setPlayersDecks(CromyDeck.getCards());
    }

    public CromyCard drawFromP2() {
        return p2.draw();
    }

    public CromyCard drawFromP1() {
        return p1.draw();
    }

    private void setPlayersDecks(List<CromyCard> cards) {
        Stack<CromyCard> shuffled = getShuffledStack(cards);
        for (int i = 0; i < (cards.size() / 2); i++) {
            p1.getCards().add(0, shuffled.pop());
            p2.getCards().add(0, shuffled.pop());
        }
    }

    public void claimBountyReward(Player player) {
        while (!emptyBounty()) {
            player.getCards().add(warBounty.pop());
        }
    }

    public void claimBountyRewardP1() {
        while (!emptyBounty()) {
            p1.getCards().add(warBounty.pop());
        }
    }

    public void claimBountyRewardP2() {
        while (!emptyBounty()) {
            p2.getCards().add(warBounty.pop());
        }
    }

    public void claimRoundRewardP1(CromyCard card1, CromyCard card2) {
        p1.getCards().add(card1);
        p1.getCards().add(card2);
    }

    public void claimRoundRewardP2(CromyCard card1, CromyCard card2) {
        p2.getCards().add(card1);
        p2.getCards().add(card2);
    }

    public boolean emptyBounty() {
        return warBounty.isEmpty();
    }

    public void addToBounty(CromyCard card) {
        warBounty.push(card);
    }

    public int deckSize(Player player) {
        return player.getCards().size();
    }

    public boolean p1HasCards() {
        return !p1.getCards().isEmpty();
    }

    public boolean p2HasCards() {
        return !p2.getCards().isEmpty();
    }

    private Stack<CromyCard> getShuffledStack(List<CromyCard> cards) {
        List<CromyCard> copy = new ArrayList<>(cards);
        Collections.copy(copy, cards);
        Collections.shuffle(copy);
        Stack<CromyCard> shuffled = new Stack<>();
        shuffled.addAll(copy);
        return shuffled;
    }
}
