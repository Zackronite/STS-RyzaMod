package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CoreChargePower;
import ryzamod.powers.EssenceOfAlchemyPower;
import ryzamod.powers.ScatterbrainedPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Scatterbrained extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Scatterbrained",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public Scatterbrained() {
        super(cardInfo);
    }

    public Scatterbrained(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ScatterbrainedPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Scatterbrained();
    }
}
