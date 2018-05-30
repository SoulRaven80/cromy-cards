package com.soulraven.cromy.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CromyDeck {

    private static Map<String, CromyCard> cards;
    static {
        cards = new HashMap<>();
        addCard(3.9, 7, 350, 270, 1.1, "A1", "Smokescreen");
        addCard(4.1, 7.5, 300, 260, 1.2, "A2", "Tracks");
        addCard(7.1, 12, 1000, 230, 1, "A3", "Optimus Prime");
        addCard(5.2, 8, 450, 180, 1.3, "A4", "Inferno");
        addCard(2.7, 1, 100, 100, 3, "B1", "Cosmos");
        addCard(2.7, 14, 400, 56, 2, "B2", "Warpath");
        addCard(7.2, 10.8, 260, 150, 1.8, "B3", "Blaster");
        addCard(6.6, 10, 360, 120, 2, "B4", "Perceptor");
        addCard(2.5, 0.25, 150, 180, 1.5, "C1", "Beachcomber");
        addCard(2.5, 0.25, 150, 180, 1.5, "C2", "Beachcomber");
        addCard(3.9, 6.8, 415, 80, 2.8, "C3", "Snarl");
        addCard(3.9, 6.9, 418, 463, 2.3, "C4", "Swoop");
        addCard(10.8, 45, 300000, 370, 2.4, "D1", "Omega Supreme");
        addCard(10.8, 45, 300000, 370, 2.4, "D2", "Omega Supreme");
        addCard(4.1, 7, 420, 85, 2.1, "D3", "Slag");
        addCard(4.1, 7, 420, 85, 2.1, "D4", "Slag");
        addCard(4.4, 10, 500, 2900, 1, "E1", "Astrotrain");
        addCard(4.7, 9, 230, 2700, 1.3, "E2", "Blitzwing");
        addCard(5.8, 8.8, 250, 2800, 1.2, "E3", "Ramjet");
        addCard(4.3, 3, 305, 150, 2.3, "E4", "Mixmaster");
        addCard(6.7, 8.7, 350, 90, 2.2, "F1", "Soundwave");
        addCard(6.7, 9.5, 980, 80, 1.1, "F2", "Megatron");
        addCard(1.8, 1, 240, 140, 1.2, "F3", "Ravage");
        addCard(1.6, 0.9, 150, 463, 1.8, "F4", "Lazerbeak");
        addCard(5.6, 5, 85, 56, 2.2, "G1", "Kickback");
        addCard(5.6, 5, 85, 56, 2.2, "G2", "Kickback");
        addCard(5.7, 8.6, 200, 2500, 1.4, "G3", "Thrust");
        addCard(5.7, 8.6, 200, 2500, 1.4, "G4", "Thrust");
        addCard(3.6, 6, 300, 80, 1.5, "H1", "Scavenger");
        addCard(3.6, 6, 300, 80, 1.5, "H2", "Scavenger");
        addCard(3.7, 4, 230, 130, 2.7, "H3", "Longhaul");
        addCard(5.1, 7, 90, 11, 1.9, "H4", "Bombshell");
    }

    public static List<CromyCard> getCards() {
        return new ArrayList<>(cards.values());
    }

    private static void addCard(double height, double weight, int strength, int speed, double transformation, String id, String name) {
        List<CromySkill> skills = new ArrayList<>();
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(height).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(weight).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(strength).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.SPEED).value(speed).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.TRANSFORMATION_SPEED).value(transformation).build());
        cards.put(id, CromyCard.builder().id(id).name(name).skills(skills).build());
    }

    public static CromyCard getCard(String id) {
        return cards.get(id);
    }
}
