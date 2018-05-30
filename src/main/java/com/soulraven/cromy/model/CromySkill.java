package com.soulraven.cromy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class CromySkill implements Comparable<CromySkill> {
    private SkillName skillName;
    private double value;

    @Override
    public int compareTo(final CromySkill other) {
        if (!skillName.getName().equals(other.skillName.getName())) {
            throw new IllegalArgumentException("Cannot compare " + skillName.getName() + " with " + other.skillName.getName());
        }
        if (skillName.skillType == SkillName.SkillType.MIN_WINS) {
            if (value - other.value < 0) {
                return 1;
            }
            else if (value - other.value > 0) {
                return -1;
            }
        } else {
            if (value - other.value > 0) {
                return 1;
            }
            else if (value - other.value < 0) {
                return -1;
            }
        }
        return 0;
    }

    @Getter
    @AllArgsConstructor
    public enum SkillName {
        HEIGHT("Height", SkillType.MAX_WINS),
        WEIGHT("Weight", SkillType.MIN_WINS),
        STRENGTH("Strength", SkillType.MAX_WINS),
        SPEED("Speed", SkillType.MAX_WINS),
        TRANSFORMATION_SPEED("Transformation Speed", SkillType.MIN_WINS);

        private String name;
        private SkillType skillType;

        private enum SkillType {
            MIN_WINS, MAX_WINS
        }
    }
}
