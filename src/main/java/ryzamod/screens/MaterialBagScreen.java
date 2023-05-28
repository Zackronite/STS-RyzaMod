package ryzamod.screens;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;

public class MaterialBagScreen extends ViewCardPoolScreen {
    private static final UIStrings uiStrings;

    public static class Enums {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen MATERIAL_BAG;
    }

    public MaterialBagScreen() {
        super(RyzaCharacter.materials, uiStrings, Enums.MATERIAL_BAG, true);
    }

    public void open() {
        super.open();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("MaterialBagScreen"));
    }
}
