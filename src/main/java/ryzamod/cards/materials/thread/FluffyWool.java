package ryzamod.cards.materials.thread;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class FluffyWool extends MaterialCard {
    public FluffyWool() {
        super("FluffyWool", MaterialCategory.THREAD, 1, 0, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FluffyWool();
    }
}
