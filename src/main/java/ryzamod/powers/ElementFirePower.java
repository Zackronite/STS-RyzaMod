package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.RyzaMod;
import ryzamod.actions.ElementFireAction;

public class ElementFirePower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("ElementFire");
    private static final PowerStrings powerStrings;
    private static final int DMG_DEALT = 3;

    public ElementFirePower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        if (this.amount >= 999) {
            this.amount = 999;
        }
        if (this.amount <= -999) {
            this.amount = -999;
        }
        this.canGoNegative = false;
        this.updateDescription();
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + DMG_DEALT + powerStrings.DESCRIPTIONS[1] + this.amount;
        if (this.amount == 1) {
            this.description += powerStrings.DESCRIPTIONS[2];
        } else {
            this.description += powerStrings.DESCRIPTIONS[3];
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
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer player = (AbstractPlayer) this.owner;
            this.flash();
            addToTop(new ElementFireAction(new DamageInfo(player, DMG_DEALT, DamageInfo.DamageType.THORNS), this.amount));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
