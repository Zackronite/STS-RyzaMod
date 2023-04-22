package ryzamod.cards.materials.flowers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class LuckyClover extends MaterialCard {
    public LuckyClover() {
        super("LuckyClover", MaterialCategory.FLOWERS, 0, 0, 2, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new LuckyClover();
    }
}
