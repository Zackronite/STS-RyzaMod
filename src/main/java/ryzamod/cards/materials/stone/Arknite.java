package ryzamod.cards.materials.stone;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class Arknite extends MaterialCard {
    public Arknite() {
        super("Arknite", MaterialCategory.STONE, 1, 1, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Arknite();
    }
}
