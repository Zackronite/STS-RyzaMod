package ryzamod.cards.materials.magical;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class HeavenlyString extends MaterialCard {
    public HeavenlyString() {
        super("HeavenlyString", MaterialCategory.MAGICAL, 0, 1, 0, 2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new HeavenlyString();
    }
}
