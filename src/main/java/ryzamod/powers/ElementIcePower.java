package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;

public class ElementIcePower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("ElementIce");
    private static final PowerStrings powerStrings;
    private static final int DMG_BLOCKED = 2;

    public ElementIcePower(AbstractCreature owner, int amount) {
        super(POWER_ID, AbstractPower.PowerType.BUFF, false, owner, amount);
        this.updateDescription();
        this.amount = amount;
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + DMG_BLOCKED * this.amount + powerStrings.DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            AbstractPlayer player = (AbstractPlayer) this.owner;
            this.flash();
            addToBot(new GainBlockAction(player, player, DMG_BLOCKED * this.amount, true));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
