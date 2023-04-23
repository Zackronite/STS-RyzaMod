package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.cards.BaseCard;
import ryzamod.util.CardInfo;

public abstract class CraftCard extends BaseCard {
    public CraftCard(CardInfo cardInfo) {
        super(cardInfo);
        this.exhaust = true;
    }

    public void applyPowers() {
        AbstractPower strength = AbstractDungeon.player.getPower("Strength");
        int realStrength = 0;
        if (strength != null) {
            realStrength = strength.amount;
            strength.amount = 0;
        }

        super.applyPowers();

        if (strength != null) {
            strength.amount = realStrength;
        }
    }
}
