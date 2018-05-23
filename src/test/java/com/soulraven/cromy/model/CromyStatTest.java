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

        List<CromyStat> stats = new ArrayList<>();
        stats.add(first);
        stats.add(second);

        Collections.sort(stats);

        assertEquals("Unexpected object found", first, stats.get(0));
    }

    @Test
    public void compareToMax() {

        CromyStat first = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(1).build();
        CromyStat second = CromyStat.builder().statName(CromyStat.StatName.STRENGTH).value(200).build();

        List<CromyStat> stats = new ArrayList<>();
        stats.add(first);
        stats.add(second);

        Collections.sort(stats);

        assertEquals("Unexpected object found", second, stats.get(0));
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