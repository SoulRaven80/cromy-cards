package com.soulraven.cromy.model;

import lombok.Builder;
import lombok.Data;

import java.util.Iterator;
import java.util.List;

@Data
@Builder
public class CromyCard {

    private String id;
    private String name;
    //    private String imageUrl;
    private List<CromySkill> skills;

    public CromySkill getSkill(String statName) {
        Iterator<CromySkill> iterator = skills.iterator();
        while (iterator.hasNext()) {
            CromySkill next = iterator.next();
            if (next.getSkillName().getName().equals(statName)) {
                return next;
            }
        }
        throw new IllegalArgumentException("Invalid SkillName: " + name);
    }
    public CromySkill getSkill(CromySkill.SkillName name) {
        Iterator<CromySkill> iterator = skills.iterator();
        while (iterator.hasNext()) {
            CromySkill next = iterator.next();
            if (next.getSkillName().getName().equals(name.getName())) {
                return next;
            }
        }
        throw new IllegalArgumentException("Invalid SkillName: " + name);
    }
}
