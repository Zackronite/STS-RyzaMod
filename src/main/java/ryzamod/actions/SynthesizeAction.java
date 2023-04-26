package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;
import com.megacrit.cardcrawl.vfx.combat.CardPoofEffect;
import ryzamod.RyzaMod;
import ryzamod.cards.crafts.*;
import ryzamod.cards.materials.ElementType;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.MaterialLibrary;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ElementFirePower;
import ryzamod.powers.ElementIcePower;
import ryzamod.powers.ElementLightningPower;
import ryzamod.powers.ElementWindPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SynthesizeAction extends AbstractGameAction {
    private boolean didSynthesize;

    private static final UIStrings uiStrings;
    private static final String[] TEXT;
    public static final int MAX_NUM_MATERIALS = 4;

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

            for (AbstractCard card : RyzaCharacter.materials.group) {
                card.stopGlowing();
                card.unhover();
                card.unfadeOut();
            }

            AbstractDungeon.gridSelectScreen.open(RyzaCharacter.materials, this.amount, true, TEXT[0]);
            this.tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0 && !this.didSynthesize) {
            HashMap<MaterialCategory, Integer> ingredients = new HashMap<>();

            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                MaterialCategory cat = ((MaterialCard)card).category;
                ingredients.put(cat, ingredients.getOrDefault(cat, 0) + 1);
            }
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                RyzaCharacter.materials.removeCard(card);
                addToBot(new VFXAction(new CardPoofEffect((float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F)));
                addToBot(new SFXAction("ORB_SLOT_GAIN"));
            }

            CraftCard craft = getCraftFromRecipe(ingredients);

            if (craft != null) {
                if (AbstractDungeon.player.hand.size() < 10) {
                    addToBot(new VFXAction(new ShowCardAndAddToHandEffect(craft, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F)));
                } else {
                    addToBot(new VFXAction(new ShowCardAndAddToDiscardEffect(craft, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F)));
                }

                for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                    HashMap<ElementType, Integer> evs = ((MaterialCard)card).elementValues;
                    for (ElementType e : evs.keySet()) {
                        int ev = evs.get(e);
                        if (ev != 0) {
                            switch (e) {
                                case FIRE:
                                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElementFirePower(AbstractDungeon.player, ev)));
                                    break;
                                case ICE:
                                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElementIcePower(AbstractDungeon.player, ev)));
                                    break;
                                case WIND:
                                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElementWindPower(AbstractDungeon.player, ev)));
                                    break;
                                case LIGHTNING:
                                    addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ElementLightningPower(AbstractDungeon.player, ev)));
                            }
                        }
                    }
                }
                // addToBot(new MakeTempCardInHandAction(craft.makeCopy()));
                this.didSynthesize = true;
            }

            RyzaMod.logger.info("Bag: " + Arrays.deepToString(RyzaCharacter.materials.group.toArray()));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            AbstractDungeon.player.hand.refreshHandLayout();
        }

        this.tickDuration();
    }

    // returns the craft or null
    private CraftCard getCraftFromRecipe(HashMap<MaterialCategory, Integer> recipe) {
        for (AbstractCard c : MaterialLibrary.getAllCrafts()) {
            HashMap<MaterialCategory, Integer> craftRecipe = ((CraftCard)c).getRecipe();
            if (craftRecipe.equals(recipe)) {
                return (CraftCard) c;
            }
        }

        return null;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("SynthesizeAction"));
        TEXT = uiStrings.TEXT;
    }
}
