package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.RyzaMod;
import ryzamod.cards.crafts.CraftCard;

public class CreativityPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("CreativityPower");
    private static final PowerStrings powerStrings;

    public CreativityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, null, false, owner, amount);

        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
        this.canGoNegative = true;
        this.updateDescription();
    }

    public void updateDescription() {
        if (this.amount >= 0) {
            this.description = powerStrings.DESCRIPTIONS[0] + this.amount + powerStrings.DESCRIPTIONS[2];
            this.type = PowerType.BUFF;
        } else {
            this.description = powerStrings.DESCRIPTIONS[1] + -this.amount + powerStrings.DESCRIPTIONS[2];
            this.type = PowerType.DEBUFF;
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8f;
        this.amount += stackAmount;
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8f;
        this.amount -= reduceAmount;
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        return type == DamageInfo.DamageType.NORMAL && card instanceof CraftCard ? damage + (float)this.amount : damage;
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
