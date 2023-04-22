package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;

public class ElementWindPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("ElementWind");
    private static final PowerStrings powerStrings;
    private static final int DRAW_PER_AMT = 3;
    private static final int DRAW_AMOUNT = 1;

    private int drawCounter;

    public ElementWindPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AbstractPower.PowerType.BUFF, false, owner, amount);
        this.updateDescription();
        this.amount = this.drawCounter = amount;
        if (amount == DRAW_PER_AMT) {
            applyEffect();
        }
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + DRAW_PER_AMT + powerStrings.DESCRIPTIONS[1] + DRAW_AMOUNT + powerStrings.DESCRIPTIONS[2];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(POWER_ID)) {
            this.drawCounter += power.amount;
            if (drawCounter >= DRAW_PER_AMT) {
                applyEffect();
            }
        }
    }

    private void applyEffect() {
        drawCounter -= DRAW_PER_AMT;
        this.flash();
        addToBot(new DrawCardAction(DRAW_AMOUNT));
        addToBot(new SFXAction("POWER_FLIGHT"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
