package ryzamod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.RyzaMod;

public class LightningReflexPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("LightningReflexPower");
    private static final PowerStrings powerStrings;

    public LightningReflexPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
