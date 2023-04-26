package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.cards.BaseCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.util.CardInfo;

import java.util.HashMap;

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

    public abstract HashMap<MaterialCategory, Integer> getRecipe();

    private int rarityToInt(CardRarity r) {
        switch (r) {
            case COMMON:
                return 0;
            case UNCOMMON:
                return 1;
            case RARE:
                return 2;
            default:
                return -1;
        }
    }

    @Override
    public int compareTo(AbstractCard other) {
        if (other instanceof CraftCard) {
            return rarityToInt(this.rarity) - rarityToInt(other.rarity);
        }
        return 1;
    }
}
