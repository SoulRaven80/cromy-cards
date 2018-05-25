package com.soulraven.cromy.game;

import com.soulraven.cromy.model.CromyCard;

import java.util.*;

public class Board {

    private List<CromyCard> p1Deck = new ArrayList<>();
    private List<CromyCard> p2Deck = new ArrayList<>();
    private Stack<CromyCard> warBounty = new Stack<>();

    public CromyCard drawFromP2() {
        return p2Deck.remove(0);
    }

    public CromyCard drawFromP1() {
        return p1Deck.remove(0);
    }

    public void initBoard(List<CromyCard> cards) {
        Stack<CromyCard> shuffled = getShuffledStack(cards);
        for (int i = 0; i < shuffled.size()-1; i++) {
            p1Deck.add(0, shuffled.pop());
            p2Deck.add(0, shuffled.pop());
            i++;
        }
    }

    public void claimBountyRewardP1() {
        while (!emptyBounty()) {
            p1Deck.add(warBounty.pop());
        }
    }

    public void claimBountyRewardP2() {
        while (!emptyBounty()) {
            p2Deck.add(warBounty.pop());
        }
    }

    public void claimRoundRewardP1(CromyCard card1, CromyCard card2) {
        p1Deck.add(card1);
        p1Deck.add(card2);
    }

    public void claimRoundRewardP2(CromyCard card1, CromyCard card2) {
        p2Deck.add(card1);
        p2Deck.add(card2);
    }

    public boolean emptyBounty() {
        return warBounty.isEmpty();
    }

    public void addToBounty(CromyCard card) {
        warBounty.push(card);
    }

    public boolean p1HasCards() {
        return !p1Deck.isEmpty();
    }

    public boolean p2HasCards() {
        return !p2Deck.isEmpty();
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
