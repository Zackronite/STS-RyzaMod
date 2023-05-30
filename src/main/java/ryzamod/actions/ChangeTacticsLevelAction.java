package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.TacticsLevelPower;

public class ChangeTacticsLevelAction extends AbstractGameAction {
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
            if (tacticsLevel + this.amount < RyzaCharacter.MIN_TACTICS_LEVEL) {
                if (tacticsLevel != RyzaCharacter.MIN_TACTICS_LEVEL) {
                    addToBot(new ReducePowerAction(p, p, new TacticsLevelPower(p, 1), tacticsLevel - RyzaCharacter.MIN_TACTICS_LEVEL));
                }
            } else if (tacticsLevel + this.amount > RyzaCharacter.MAX_TACTICS_LEVEL) {
                if (tacticsLevel != RyzaCharacter.MAX_TACTICS_LEVEL) {
                    addToBot(new ApplyPowerAction(p, p, new TacticsLevelPower(p, 1), RyzaCharacter.MAX_TACTICS_LEVEL - tacticsLevel));
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
