package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;

public class ElementLightningPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("ElementLightning");
    private static final PowerStrings powerStrings;
    private static final int ENERGY_PER_AMT = 3;
    private static final int ENERGY_GAINED = 1;

    private int energyCounter;

    public ElementLightningPower(AbstractCreature owner, int amount) {
        super(POWER_ID, AbstractPower.PowerType.BUFF, false, owner, amount);
        this.updateDescription();
        this.amount = this.energyCounter = amount;
        if (amount == ENERGY_PER_AMT) {
            applyEffect();
        }
    }

    public void updateDescription() {
        this.description = powerStrings.DESCRIPTIONS[0] + ENERGY_PER_AMT + powerStrings.DESCRIPTIONS[1] + ENERGY_GAINED + powerStrings.DESCRIPTIONS[2];
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(POWER_ID)) {
            this.energyCounter += power.amount;
            if (energyCounter >= ENERGY_PER_AMT) {
                applyEffect();
            }
        }
    }

    private void applyEffect() {
        energyCounter -= ENERGY_PER_AMT;
        this.flash();
        addToBot(new GainEnergyAction(ENERGY_GAINED));
        addToBot(new SFXAction("AUTOMATON_ORB_SPAWN"));
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
