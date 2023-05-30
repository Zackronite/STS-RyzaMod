package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ChainPower;

public class ChangeChainAction extends AbstractGameAction {
    public static final AbstractPlayer p = AbstractDungeon.player;

    public ChangeChainAction(int amount) {
        this.amount = p.hasPower(RyzaMod.makeID("LightningReflexPower")) ? amount * 2 : amount;
        this.actionType = ActionType.POWER;
    }

    @Override
    public void update() {
        String chainID = RyzaMod.makeID("Chain");

        if (p.hasPower(chainID)) {
            int chain = p.getPower(chainID).amount;
            if (chain + this.amount < RyzaCharacter.MIN_CHAIN_LEVEL) {
                if (chain != RyzaCharacter.MIN_CHAIN_LEVEL) {
                    addToBot(new ReducePowerAction(p, p, new ChainPower(p, 1), chain - RyzaCharacter.MIN_CHAIN_LEVEL));
                }
            } else if (chain + this.amount > RyzaCharacter.MAX_CHAIN_LEVEL) {
                if (chain != RyzaCharacter.MAX_CHAIN_LEVEL) {
                    addToBot(new ApplyPowerAction(p, p, new ChainPower(p, 1), RyzaCharacter.MAX_CHAIN_LEVEL - chain));
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
}
