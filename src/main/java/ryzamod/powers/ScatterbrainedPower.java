package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.cards.tempCards.Star;

import static ryzamod.RyzaMod.makeID;

public class ScatterbrainedPower extends BasePower {
    public static final String POWER_ID = makeID("ScatterbrainedPower");
    private static final PowerStrings powerStrings;

    public ScatterbrainedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            if (this.owner instanceof AbstractPlayer) {
                AbstractPlayer p = (AbstractPlayer) this.owner;
                this.flash();
                addToBot(new ApplyPowerAction(p, p, new CreativityPower(p, -1)));
                addToBot(new DrawCardAction(p, 2));
            }
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
