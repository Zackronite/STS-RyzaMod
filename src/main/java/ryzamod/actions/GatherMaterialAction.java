package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import ryzamod.RyzaMod;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.MaterialLibrary;
import ryzamod.character.RyzaCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GatherMaterialAction extends AbstractGameAction {
    private boolean isRandom;
    private boolean didGather;
    private ArrayList<MaterialCategory> validCategories;

    private static final UIStrings uiStrings;
    private static final String[] TEXT;

    public GatherMaterialAction(int amount, boolean isRandom, ArrayList<MaterialCategory> validCategories) {
        this.amount = amount;
        this.isRandom = isRandom;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.validCategories = validCategories;
        this.duration = Settings.ACTION_DUR_FAST;
        this.didGather = false;
    }

    public GatherMaterialAction(int amount, boolean isRandom) {
        this(amount, isRandom, MaterialCategory.getCategoriesOfRarity(new int[]{0, 1, 2}));
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (RyzaCharacter.materials.size() == RyzaCharacter.maxNumMaterials) {
                this.isDone = true;
                return;
            }

            if (!this.isRandom) {
                CardGroup tmp = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
                ArrayList<AbstractCard> matChoice  = MaterialLibrary.getAllMaterialsOfCategory(validCategories);
                Collections.sort(matChoice);

                for (AbstractCard m : matChoice) {
                    tmp.addToTop(m);
                }
                AbstractDungeon.gridSelectScreen.open(tmp, this.amount, true, TEXT[0]);
                this.tickDuration();
                return;
            }

            for (int i = 0; i < this.amount; i++) {
                MaterialCard matToGain = (MaterialCard) MaterialLibrary.getRandomMaterial(validCategories);
                if (RyzaCharacter.materials.size() == RyzaCharacter.maxNumMaterials) {
                    this.isDone = true;
                    return;
                }

                // TODO: maybe add different sfx for different materials?
                addToBot(new SFXAction("RELIC_DROP_MAGICAL"));
                RyzaCharacter.materials.addToTop(matToGain);
            }
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0 && !this.didGather) {
            for (AbstractCard mat : AbstractDungeon.gridSelectScreen.selectedCards) {
                MaterialCard matToGain = (MaterialCard) mat;
                if (RyzaCharacter.materials.size() == RyzaCharacter.maxNumMaterials) {
                    this.isDone = true;
                    return;
                }

                // TODO: maybe add different sfx for different materials?
                addToBot(new SFXAction("RELIC_DROP_MAGICAL"));

                RyzaCharacter.materials.addToTop(matToGain);
            }

            RyzaMod.logger.info("Bag: " + Arrays.deepToString(RyzaCharacter.materials.group.toArray()));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.tickDuration();
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("GatherMaterialAction"));
        TEXT = uiStrings.TEXT;
    }
}
