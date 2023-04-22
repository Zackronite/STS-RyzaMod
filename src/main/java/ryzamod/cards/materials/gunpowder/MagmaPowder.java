package ryzamod.cards.materials.gunpowder;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class MagmaPowder extends MaterialCard {
    public MagmaPowder() {
        super("MagmaPowder", MaterialCategory.GUNPOWDER, 1, 0, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagmaPowder();
    }
}
