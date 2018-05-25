package com.soulraven.cromy.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class CromyStatTest {

    @Test
    public void compareToMin() {
        CromyStat first = CromyStat.builder().statName(CromyStat.StatName.WEIGHT).value(1).build();
        CromyStat second = CromyStat.builder().statName(CromyStat.StatName.WEIGHT).value(200).build();

        assertEquals("Unexpected comparison", 1, first.compareTo(second));
        assertEquals("Unexpected comparison", -1, second.compareTo(first));
    }

    @Test
    public void compareToMax() {

        CromyStat first = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(1).build();
        CromyStat second = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(200).build();

        assertEquals("Unexpected comparison", -1, first.compareTo(second));
        assertEquals("Unexpected comparison", 1, second.compareTo(first));
    }

    @Test
    public void compareToEqual() {

        CromyStat first = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(10).build();
        CromyStat second = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(10).build();

        assertEquals("Unexpected comparison", 0, first.compareTo(second));
        assertEquals("Unexpected comparison", 0, second.compareTo(first));
    }

    @Test(expected = IllegalArgumentException.class)
    public void compareToIllegalArgs() {

        CromyStat first = CromyStat.builder().statName(CromyStat.StatName.WEIGHT).value(1).build();
        CromyStat second = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(200).build();

        List<CromyStat> stats = new ArrayList<>();
        stats.add(first);
        stats.add(second);

        Collections.sort(stats);
    }
}