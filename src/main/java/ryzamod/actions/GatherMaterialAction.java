package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ryzamod.RyzaMod;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.MaterialLibrary;
import ryzamod.character.RyzaCharacter;
import ryzamod.vfx.ShowMaterialEffect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
                materialBagIsFullDialog();
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

            // pick random materials
            ArrayList<MaterialCard> mats = new ArrayList<>();
            for (int i = 0; i < this.amount; i++) {
                mats.add((MaterialCard) MaterialLibrary.getRandomMaterial(validCategories));
            }

            addMaterial(mats);

            this.didGather = true;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0 && !this.didGather) {
            // pick the player's selected materials
            ArrayList<MaterialCard> mats = new ArrayList<>();
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                mats.add((MaterialCard) card);
                card.unhover();
                card.stopGlowing();
                card.unfadeOut();
            }

            addMaterial(mats);

            this.didGather = true;
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        this.tickDuration();
    }

    private void checkPowers() {
        AbstractPlayer p = AbstractDungeon.player;
        if (p.hasPower(RyzaMod.makeID("MilkyWayPower"))) {
            addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, p.getPower(RyzaMod.makeID("MilkyWayPower")).amount)));
        }
    }

    private void addMaterial(List<MaterialCard> m) {
        if (RyzaCharacter.materials.size() + m.size() > RyzaCharacter.maxNumMaterials) {
            materialBagIsFullDialog();
            m = m.subList(0, RyzaCharacter.maxNumMaterials - RyzaCharacter.materials.size());
        }

        // get screen positions for materials (wont work well for super large num. (>5?) of materials at the same time)
        // can adjust later if needed
        float[][] positions = new float[m.size()][2];
        final float MAT_OFFSET = 350f;
        final float MAT_OFFSET_ALT = MAT_OFFSET / 2f;

        for (int i = 0; i < m.size(); i++) {
            int middle;

            positions[i][0] = Settings.WIDTH / 2f;
            positions[i][1] = Settings.HEIGHT / 2f;

            if (m.size() % 2 == 0) {
                middle = m.size() / 2;
                if (i == middle) {
                    positions[i][0] += MAT_OFFSET_ALT * Settings.scale;
                } else if (i == middle - 1) {
                    positions[i][0] -= MAT_OFFSET_ALT * Settings.scale;
                } else if (i > middle) {
                    positions[i][0] += ((MAT_OFFSET * (i - middle)) + MAT_OFFSET_ALT) * Settings.scale;
                } else {
                    positions[i][0] -= ((MAT_OFFSET * ((middle - 1) - i)) + MAT_OFFSET_ALT) * Settings.scale;
                }
            } else {
                middle = (m.size() - 1) / 2;
                if (i > middle) {
                    positions[i][0] += (MAT_OFFSET * (i - middle)) * Settings.scale;
                } else if (i < middle) {
                    positions[i][0] -= (MAT_OFFSET * (middle - i)) * Settings.scale;
                }
            }
        }

        for (int i = 0; i < m.size(); i ++) {
            MaterialCard mat = m.get(i);
            // TODO: maybe add different sfx for different materials?
            addToTop(new SFXAction("RELIC_DROP_MAGICAL"));
            addToTop(new VFXAction(new ShowMaterialEffect(mat, positions[i][0], positions[i][1])));
            RyzaCharacter.materials.addToTop(mat);
            checkPowers();
        }
    }

    private void materialBagIsFullDialog() {
        if (AbstractDungeon.player instanceof RyzaCharacter) {
            ((RyzaCharacter)AbstractDungeon.player).createMaterialBagFullDialog();
        }
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("GatherMaterialAction"));
        TEXT = uiStrings.TEXT;
    }
}
