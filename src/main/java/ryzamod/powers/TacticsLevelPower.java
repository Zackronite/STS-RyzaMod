package ryzamod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;

public class TacticsLevelPower extends BasePower {
    public static final String POWER_ID = RyzaMod.makeID("Tactics Level");
    private static final PowerStrings powerStrings;

    public TacticsLevelPower(AbstractCreature owner, int amount) {
        super(POWER_ID, PowerType.BUFF, false, owner, amount);
        this.description = powerStrings.DESCRIPTIONS[0];
        if (this.amount >= RyzaCharacter.MAX_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MAX_TACTICS_LEVEL;
        }
        if (this.amount <= RyzaCharacter.MIN_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MIN_TACTICS_LEVEL;
        }
        this.canGoNegative = false;
    }

    @Override
    public void atEndOfRound() {
        if (this.owner.isPlayer) {
            AbstractPlayer player = (AbstractPlayer) owner;
            if (!player.hasPower(RyzaMod.makeID("MindfulnessPower"))) {
                if (this.amount > RyzaCharacter.MIN_TACTICS_LEVEL) {
                    addToBot(new ReducePowerAction(this.owner, this.owner, this.ID, this.amount - RyzaCharacter.MIN_TACTICS_LEVEL));
                }
            }
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8f;
        this.amount += stackAmount;
        if (this.amount >= RyzaCharacter.MAX_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MAX_TACTICS_LEVEL;
        }
        if (this.amount <= RyzaCharacter.MIN_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MIN_TACTICS_LEVEL;
        }
    }

    public void reducePower(int reduceAmount) {
        this.fontScale = 8f;
        this.amount -= reduceAmount;
        if (this.amount >= RyzaCharacter.MAX_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MAX_TACTICS_LEVEL;
        }
        if (this.amount <= RyzaCharacter.MIN_TACTICS_LEVEL) {
            this.amount = RyzaCharacter.MIN_TACTICS_LEVEL;
        }
    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    }
}
