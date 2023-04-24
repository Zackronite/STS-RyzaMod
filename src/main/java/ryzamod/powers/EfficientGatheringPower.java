package ryzamod.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.actions.GatherMaterialAction;

import static ryzamod.RyzaMod.makeID;

public class EfficientGatheringPower extends BasePower {
    public static final String POWER_ID = makeID("EfficientGatheringPower");
    private static final PowerStrings powerStrings;

    public EfficientGatheringPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
    }
    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new GatherMaterialAction(1, true));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
