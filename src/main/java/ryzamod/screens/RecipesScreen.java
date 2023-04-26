package ryzamod.screens;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;

public class RecipesScreen extends ViewCardPoolScreen {
    private static final UIStrings uiStrings;

    public static class Enums {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen RECIPES;
    }

    public RecipesScreen() {
        super(RyzaCharacter.recipes, uiStrings, Enums.RECIPES);
    }

    public void open() {
        super.open();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("RecipesScreen"));
    }
}
