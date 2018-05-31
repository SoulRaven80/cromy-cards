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
    private List<CromySkill> skills;

    public CromySkill getSkill(String skillName) {
        Iterator<CromySkill> iterator = skills.iterator();
        while (iterator.hasNext()) {
            CromySkill next = iterator.next();
            if (next.getSkillName().getName().equals(skillName)) {
                return next;
            }
        }
        throw new IllegalArgumentException("Invalid SkillName: " + name);
    }
    public CromySkill getSkill(CromySkill.SkillName skillName) {
        return getSkill(skillName.getName());
    }
}
