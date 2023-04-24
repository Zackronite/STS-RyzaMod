package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.cards.tempCards.Star;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ShiningTrailPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class ShiningTrail extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShiningTrail",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public ShiningTrail() {
        super(cardInfo);
        this.cardsToPreview = new Star();
    }

    public ShiningTrail(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new ShiningTrailPower(p, 1), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShiningTrail();
    }
}
