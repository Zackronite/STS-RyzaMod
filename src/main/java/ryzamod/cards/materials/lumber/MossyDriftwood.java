package ryzamod.cards.materials.lumber;

import com.megacrit.cardcrawl.cards.AbstractCard;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;

public class MossyDriftwood extends MaterialCard {
    public MossyDriftwood() {
        super("MossyDriftwood", MaterialCategory.LUMBER, 0, 1, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MossyDriftwood();
    }
}
