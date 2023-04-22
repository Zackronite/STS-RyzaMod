package ryzamod.cards.materials.poisons;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class TemptingSap extends MaterialCard {
    public TemptingSap() {
        super("TemptingSap", MaterialCategory.POISONS, 1, 1, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new TemptingSap();
    }
}
