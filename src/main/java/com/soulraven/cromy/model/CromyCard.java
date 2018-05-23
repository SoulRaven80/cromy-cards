package com.soulraven.cromy.model;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class CromyCard {

    private String name;
    //    private String imageUrl;
    private List<CromyStat> stats;

    public CromyStat getStat(CromyStat.StatName name) {
        Iterator<CromyStat> iterator = stats.iterator();
        while (iterator.hasNext()) {
            CromyStat next = iterator.next();
            if (next.getStatName().getName().equals(name.getName())) {
                return next;
            }
        }
        throw new IllegalArgumentException("Invalid StatName: " + name);
    }
}
