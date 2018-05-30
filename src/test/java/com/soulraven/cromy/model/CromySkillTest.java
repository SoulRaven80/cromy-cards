package com.soulraven.cromy.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CromySkillTest {

    @Test
    public void compareToMin() {
        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(200).build();

        assertEquals("Unexpected comparison", 1, first.compareTo(second));
        assertEquals("Unexpected comparison", -1, second.compareTo(first));
    }

    @Test
    public void compareToMax() {

        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(200).build();

        assertEquals("Unexpected comparison", -1, first.compareTo(second));
        assertEquals("Unexpected comparison", 1, second.compareTo(first));
    }

    @Test
    public void compareToEqual() {

        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(10).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(10).build();

        assertEquals("Unexpected comparison", 0, first.compareTo(second));
        assertEquals("Unexpected comparison", 0, second.compareTo(first));
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareToIllegalArgs() {

        CromySkill first = CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(1).build();
        CromySkill second = CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(200).build();

        List<CromySkill> stats = new ArrayList<>();
        stats.add(first);
        stats.add(second);

        Collections.sort(stats);
    }
}