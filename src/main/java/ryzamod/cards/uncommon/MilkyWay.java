package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.MilkyWayPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class MilkyWay extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MilkyWay",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public MilkyWay() {
        super(cardInfo);
    }

    public MilkyWay(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MilkyWayPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MilkyWay();
    }
}
