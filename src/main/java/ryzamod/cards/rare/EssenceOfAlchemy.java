package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CoreChargePower;
import ryzamod.powers.EssenceOfAlchemyPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class EssenceOfAlchemy extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "EssenceOfAlchemy",
            3,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public EssenceOfAlchemy() {
        super(cardInfo);
    }

    public EssenceOfAlchemy(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new EssenceOfAlchemyPower(p, 1)));
        addToBot(new ApplyPowerAction(p, p, new CoreChargePower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EssenceOfAlchemy();
    }
}
