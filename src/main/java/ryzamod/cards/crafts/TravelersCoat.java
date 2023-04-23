package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class TravelersCoat extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TravelersCoat",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 8;

    public TravelersCoat() {
        super(cardInfo);

        setBlock(BLOCK);
    }

    public TravelersCoat(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, BLOCK));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TravelersCoat();
    }
}
