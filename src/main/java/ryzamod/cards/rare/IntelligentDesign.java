package ryzamod.cards.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.IntelligentDesignAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class IntelligentDesign extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "IntelligentDesign",
            4,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public IntelligentDesign() {
        super(cardInfo);
        this.exhaust = true;
    }

    public IntelligentDesign(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new IntelligentDesignAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new IntelligentDesign();
    }
}
