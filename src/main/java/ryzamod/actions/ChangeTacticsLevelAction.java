package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ryzamod.RyzaMod;
import ryzamod.powers.TacticsLevelPower;

public class ChangeTacticsLevelAction extends AbstractGameAction {
    public static final int MIN_TACTICS = 1;
    public static final int MAX_TACTICS = 5;

    public ChangeTacticsLevelAction(int amount) {
        this.amount = amount;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        AbstractPlayer p;
        p = AbstractDungeon.player;
        String tacticsID = RyzaMod.makeID("Tactics Level");

        if (p.hasPower(tacticsID)) {
            int tacticsLevel = p.getPower(tacticsID).amount;
            if (tacticsLevel + this.amount < MIN_TACTICS) {
                if (tacticsLevel != MIN_TACTICS) {
                    addToBot(new ReducePowerAction(p, p, new TacticsLevelPower(p, 1), tacticsLevel - MIN_TACTICS));
                }
            } else if (tacticsLevel + this.amount > MAX_TACTICS) {
                if (tacticsLevel != MAX_TACTICS) {
                    addToBot(new ApplyPowerAction(p, p, new TacticsLevelPower(p, 1), MAX_TACTICS - tacticsLevel));
                }
            } else {
                if (this.amount > 0) {
                    addToBot(new ApplyPowerAction(p, p, new TacticsLevelPower(p, 1), this.amount));
                } else {
                    addToBot(new ReducePowerAction(p, p, new TacticsLevelPower(p, 1), this.amount));
                }
            }
        }

        this.isDone = true;
    }
}
