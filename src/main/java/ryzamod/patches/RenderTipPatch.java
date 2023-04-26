package ryzamod.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import ryzamod.cards.crafts.CraftCard;
import ryzamod.screens.RecipesScreen;

import java.util.ArrayList;

@SpirePatch(
        clz = TipHelper.class,
        method = "renderTipForCard"
)
public class RenderTipPatch {
    @SpirePrefixPatch
    public static void ShowRecipe(AbstractCard c, SpriteBatch sb, ArrayList<String> keywords) {
        if (c instanceof CraftCard) {
            String cardRecipeTip = c.cardID.toLowerCase();
            if (AbstractDungeon.player == null || (AbstractDungeon.screen == RecipesScreen.Enums.RECIPES)) {
                if (!keywords.contains(cardRecipeTip)) {
                    keywords.add(cardRecipeTip);
                }
            } else {
                keywords.remove(cardRecipeTip);
            }
        }
    }
}
