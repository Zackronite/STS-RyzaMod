package ryzamod.cards.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.SynthesizeAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Tinker extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Tinker",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;
    public Tinker() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public Tinker(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SynthesizeAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Tinker();
    }
}
