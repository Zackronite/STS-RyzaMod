package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.AlchemistFormPower;
import ryzamod.powers.EfficientGatheringPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class AlchemistForm extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AlchemistForm",
            3,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public static final int AMT = 1;
    public static final int UPG_AMT = 1;

    public AlchemistForm() {
        super(cardInfo);
        setMagic(AMT, UPG_AMT);
    }

    public AlchemistForm(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AlchemistFormPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AlchemistForm();
    }
}
