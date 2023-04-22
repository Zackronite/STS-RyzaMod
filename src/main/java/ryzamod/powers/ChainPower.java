package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.RyzaMod;

public class ChainPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("Chain");
    private static final PowerStrings powerStrings;

    public ChainPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    @Override
    public void atEndOfRound() {
        if (this.owner.isPlayer) {
            AbstractPlayer player = (AbstractPlayer) owner;
            if (this.amount > 1) {
                addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount - 1));
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
