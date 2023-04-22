package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ryzamod.RyzaMod;
import ryzamod.powers.ChainPower;

public class ChangeChainAction extends AbstractGameAction {
    public static final int MIN_CHAIN = 1;
    public static final int MAX_CHAIN = 5;

    public ChangeChainAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.POWER;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        AbstractPlayer p;
        if (this.duration == Settings.ACTION_DUR_XFAST) {
            p = AbstractDungeon.player;
            String chainID = RyzaMod.makeID("Chain");

            if (p.hasPower(chainID)) {
                int chain = p.getPower(chainID).amount;
                if (chain + this.amount < MIN_CHAIN) {
                    if (chain != MIN_CHAIN) {
                        addToBot(new ReducePowerAction(p, p, new ChainPower(p, 1), chain - MIN_CHAIN));
                    }
                } else if (chain + this.amount > MAX_CHAIN) {
                    if (chain != MAX_CHAIN) {
                        addToBot(new ApplyPowerAction(p, p, new ChainPower(p, 1), MAX_CHAIN - chain));
                    }
                } else {
                    if (this.amount > 0) {
                        addToBot(new ApplyPowerAction(p, p, new ChainPower(p, 1), this.amount));
                    } else {
                        addToBot(new ReducePowerAction(p, p, new ChainPower(p, 1), this.amount));
                    }
                }
            }

            this.isDone = true;
        }

        this.tickDuration();
    }
}
