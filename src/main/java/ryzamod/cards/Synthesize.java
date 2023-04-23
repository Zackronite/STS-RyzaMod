package ryzamod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.SynthesizeAction;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Synthesize extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Synthesize",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int UPG_COST = 0;

    public Synthesize() {
        super(cardInfo);

        setCostUpgrade(UPG_COST);
    }

    public Synthesize(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new SynthesizeAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Synthesize();
    }
}

