package com.soulraven.cromy.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class CromyStat implements Comparable<CromyStat> {
    private StatName statName;
    private double value;

    @Override
    public int compareTo(final CromyStat other) {
        if (!statName.getName().equals(other.statName.getName())) {
            throw new IllegalArgumentException("Cannot compare " + statName.getName() + " with " + other.statName.getName());
        }
        if (statName.statType == StatName.StatType.MIN_WINS) {
            if (value - other.value > 0) {
                return 1;
            }
            else if (value - other.value < 0) {
                return -1;
            }
            return 0;
        } else {
            if (other.value - value > 0) {
                return 1;
            }
            else if (other.value - value < 0) {
                return -1;
            }
            return 0;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum StatName {
        HEIGHT("Height", StatType.MAX_WINS),
        WEIGHT("Weight", StatType.MIN_WINS),
        STRENGTH("Strength", StatType.MAX_WINS),
        SPEED("Speed", StatType.MAX_WINS),
        TRANSFORMATION_SPEED("Transformation Speed", StatType.MIN_WINS);

        private String name;
        private StatType statType;

        private enum StatType {
            MIN_WINS, MAX_WINS
        }
    }
}
