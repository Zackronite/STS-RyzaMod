package ryzamod.cards.materials;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MagmaPowder extends MaterialCard {
    public MagmaPowder() {
        super("MagmaPowder", MaterialCategory.GUNPOWDER, 1, 0, 0, 0);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MagmaPowder();
    }
}
