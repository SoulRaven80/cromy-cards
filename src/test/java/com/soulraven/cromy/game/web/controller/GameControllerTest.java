package com.soulraven.cromy.game.web.controller;

import com.soulraven.cromy.model.CromyCard;
import com.soulraven.cromy.model.CromyDeck;
import com.soulraven.cromy.model.CromySkill;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(PowerMockRunner.class)
@SpringBootTest
@PowerMockRunnerDelegate(SpringRunner.class)
@AutoConfigureMockMvc
@PrepareForTest(CromyDeck.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        PowerMockito.spy(CromyDeck.class);
        List<CromyCard> cards = new ArrayList<>();
        addCard(3.9, 7, 350, 270, 1.1, "A1", "Smokescreen", cards);
        addCard(4.1, 7.5, 300, 260, 1.2, "A2", "Tracks", cards);
        Mockito.when(CromyDeck.getCards()).thenReturn(cards);
    }

    private void addCard(double height, double weight, int strength, int speed, double transformation, String id, String name, List<CromyCard> cards) {
        List<CromySkill> skills = new ArrayList<>();
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.HEIGHT).value(height).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.WEIGHT).value(weight).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.STRENGTH).value(strength).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.SPEED).value(speed).build());
        skills.add(CromySkill.builder().skillName(CromySkill.SkillName.TRANSFORMATION_SPEED).value(transformation).build());
        cards.add(CromyCard.builder().id(id).name(name).skills(skills).build());
    }

    @Test
    public void startNewGame() {
    }

    @Test
    public void board() throws Exception {
        mockMvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("p1DeckSize"))
                .andExpect(model().attributeExists("p2DeckSize"))
                .andExpect(model().attributeExists("p1Number"))
                .andExpect(model().attributeExists("p2Number"))
                .andExpect(model().attributeExists("gameForm"))
                .andExpect(model().attributeExists("card"))
                .andExpect(content().string(Matchers.containsString("<title>Cromy game</title>")));
    }

    @Test
    public void tied() throws Exception {
        mockMvc.perform(get("/tied")
                .param("cardId", "A1")
                .param("skillName", CromySkill.SkillName.WEIGHT.getName()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("p1DeckSize"))
                .andExpect(model().attributeExists("p2DeckSize"))
                .andExpect(model().attributeExists("p1Number"))
                .andExpect(model().attributeExists("p2Number"))
                .andExpect(model().attributeExists("gameForm"))
                .andExpect(model().attributeExists("p1Draw"))
                .andExpect(model().attributeExists("skill"))
                .andExpect(model().attributeExists("p2Draw"))
                .andExpect(model().attributeExists("result"))
                .andExpect(content().string(Matchers.containsString("<title>Cromy game: Draw</title>")));
    }

    @Test
    // TODO ensure cards list drawn from deck
    public void selectCard() throws Exception {
        mockMvc.perform(post("/selectCard")
                .param("cardId", "A1")
                .param("skillName", CromySkill.SkillName.WEIGHT.getName()))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("p1DeckSize"))
                .andExpect(model().attributeExists("p2DeckSize"))
                .andExpect(model().attributeExists("p1Number"))
                .andExpect(model().attributeExists("p2Number"))
                .andExpect(model().attributeExists("gameForm"))
                .andExpect(model().attributeExists("p1Draw"))
                .andExpect(model().attributeExists("skill"))
                .andExpect(model().attributeExists("p2Draw"))
                .andExpect(model().attributeExists("result"))
                .andExpect(content().string(Matchers.containsString("<title>Cromy game: Draw</title>")));
//        Mockito.verify(mockObj, times(2)).methodToMock();
    }
}