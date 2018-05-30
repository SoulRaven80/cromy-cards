package com.soulraven.cromy.game;

import com.soulraven.cromy.model.CromyCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Player {
    private String number;
    private List<CromyCard> cards;

    public CromyCard draw() {
        return cards.remove(0);
    }

}
