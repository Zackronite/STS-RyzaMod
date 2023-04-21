package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import ryzamod.cards.common.ExplosiveUni;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SynthesizeAction extends AbstractGameAction {
    public static final int MAX_NUM_MATERIALS = 4;
    public static final HashMap<ArrayList<MaterialCategory>, AbstractCard> recipes;

    private boolean didSynthesize;
    public SynthesizeAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
        this.amount = MAX_NUM_MATERIALS;
        this.didSynthesize = false;
    }
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (RyzaCharacter.materials.size() == 0) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(RyzaCharacter.materials, this.amount, true, "Some text");
            this.tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0 && !this.didSynthesize) {
            ArrayList<MaterialCategory> ingredients = new ArrayList<>();

            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                ingredients.add(((MaterialCard)card).category);
            }
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                RyzaCharacter.materials.removeCard(card);
                AbstractDungeon.effectList.add(new ExhaustCardEffect(card));
            }

            if (recipes.containsKey(ingredients)) {
                addToBot(new MakeTempCardInHandAction(recipes.get(ingredients)));
                addToBot(new SFXAction("ORB_SLOT_GAIN"));
                this.didSynthesize = true;
            }
        }

        this.tickDuration();
    }

    static {
        recipes = new HashMap<>();
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER)), new ExplosiveUni());
    }
}
