package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.LightningReflexPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class LightningReflex extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "LightningReflex",
            2,
            AbstractCard.CardType.POWER,
            AbstractCard.CardTarget.SELF,
            AbstractCard.CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public LightningReflex() {
        super(cardInfo);
        this.upgInnate = true;
    }

    public LightningReflex(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new LightningReflexPower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LightningReflex();
    }
}
