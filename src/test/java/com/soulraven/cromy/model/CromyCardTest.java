package com.soulraven.cromy.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CromyCardTest {

    @Test
    public void getSkillByName() {
        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(200).build();

        List<CromySkill> skills = new ArrayList<>();
        skills.add(first);
        skills.add(second);

        CromyCard card = CromyCard.builder().skills(skills).build();
        CromySkill actual = card.getSkill(CromySkill.SkillName.HEIGHT.getName());

        assertEquals("Unexpected skill", first, actual);
    }

    @Test
    public void getSkillBySkillName() {
        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(200).build();

        List<CromySkill> skills = new ArrayList<>();
        skills.add(first);
        skills.add(second);

        CromyCard card = CromyCard.builder().skills(skills).build();
        CromySkill actual = card.getSkill(CromySkill.SkillName.HEIGHT);

        assertEquals("Unexpected skill", first, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNullSkillByName() {
        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(200).build();

        List<CromySkill> skills = new ArrayList<>();
        skills.add(first);
        skills.add(second);

        CromyCard card = CromyCard.builder().skills(skills).build();
        card.getSkill(CromySkill.SkillName.SPEED.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateNullSkillBySkillName() {
        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(200).build();

        List<CromySkill> skills = new ArrayList<>();
        skills.add(first);
        skills.add(second);

        CromyCard card = CromyCard.builder().skills(skills).build();
        card.getSkill(CromySkill.SkillName.TRANSFORMATION_SPEED);
    }
}