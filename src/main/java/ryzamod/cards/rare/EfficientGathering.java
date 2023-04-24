package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.EfficientGatheringPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class EfficientGathering extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "EfficientGathering",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);



    public EfficientGathering() {
        super(cardInfo);
    }

    public EfficientGathering(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EfficientGatheringPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new EfficientGathering();
    }
}
