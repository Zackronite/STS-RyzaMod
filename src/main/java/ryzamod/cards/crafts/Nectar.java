package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class Nectar extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Nectar",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 8;

    public Nectar() {
        super(cardInfo);

        setMagic(MAGIC);
    }

    public Nectar(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new HealAction(p, p, MAGIC));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Nectar();
    }
}
