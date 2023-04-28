package ryzamod.patches;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import org.lwjgl.Sys;
import ryzamod.cards.crafts.CraftCard;
import ryzamod.cards.materials.ElementType;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.MaterialLibrary;
import ryzamod.screens.RecipesScreen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SpirePatch(
        clz = TipHelper.class,
        method = "renderTipForCard"
)
public class RenderTipPatch {
    private static boolean registeredKeywords = false;

    @SpirePrefixPatch
    public static void ShowRecipe(AbstractCard c, SpriteBatch sb, ArrayList<String> keywords) {
        String keyword = c.cardID.toLowerCase();

        if (!registeredKeywords) {
            registerRecipes();
            registerElementValues();
            registeredKeywords = true;
        }

        if (c instanceof CraftCard) {
            if (AbstractDungeon.player == null || (AbstractDungeon.screen == RecipesScreen.Enums.RECIPES)) {
                if (!keywords.contains(keyword)) {
                    keywords.add(keyword);
                }
            } else {
                keywords.remove(keyword);
            }
        }

        if (c instanceof MaterialCard) {
            if (!keywords.contains(keyword)) {
                keywords.add(keyword);
            }
        }
    }

    private static void registerRecipes() {
        for (AbstractCard c : MaterialLibrary.getAllCrafts()) {
            CraftCard craft = (CraftCard) c;
            String desc = "";
            HashMap<MaterialCategory, Integer> recipe = new HashMap<>(craft.getRecipe());
            recipe.values().removeIf(val -> val == 0);
            Iterator<MaterialCategory> it = craft.getRecipe().keySet().iterator();

            while (it.hasNext()) {
                MaterialCategory cat = it.next();
                desc += "#b" + craft.getRecipe().get(cat) + " " + cat.getName();
                if (it.hasNext()) {
                    desc += " NL ";
                }
            }
            BaseMod.addKeyword("Recipe", new String[]{c.cardID.toLowerCase()}, desc);
        }
    }

    private static void registerElementValues() {
        for (AbstractCard c : MaterialLibrary.getAllMaterials()) {
            MaterialCard mat = (MaterialCard) c;
            String desc = "";
            HashMap<ElementType, Integer> evs = new HashMap<>(mat.elementValues);
            evs.values().removeIf(val -> val == 0);
            Iterator<ElementType> it = evs.keySet().iterator();

            while (it.hasNext()) {
                ElementType e = it.next();
                desc += "#b" + mat.elementValues.get(e) + " " + e.getName();
                if (it.hasNext()) {
                    desc += " NL ";
                }
            }
            System.out.println(desc);
            BaseMod.addKeyword("Element Value", new String[]{c.cardID.toLowerCase()}, desc);
        }
    }
}
