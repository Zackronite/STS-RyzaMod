package ryzamod.powers;

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
        this.updateDescription();
        this.amount = amount;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + DMG_DEALT + powerStrings.DESCRIPTIONS[1] + this.amount;
        if (this.amount == 1) {
            this.description += powerStrings.DESCRIPTIONS[2];
        } else {
            this.description += powerStrings.DESCRIPTIONS[3];
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
