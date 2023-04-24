package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.cards.tempCards.Star;

import static ryzamod.RyzaMod.makeID;

public class ShiningTrailPower extends BasePower {
    public static final String POWER_ID = makeID("ShiningTrailPower");
    private static final PowerStrings powerStrings;

    public ShiningTrailPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
    }
    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new Star(), this.amount, false));
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}