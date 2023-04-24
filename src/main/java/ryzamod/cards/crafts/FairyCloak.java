package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class FairyCloak extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "FairyCloak",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 10;

    public FairyCloak() {
        super(cardInfo);

        setBlock(BLOCK);
    }

    public FairyCloak(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, -2)));
        addToBot(new ApplyPowerAction(p, p, new IntangiblePower(p, 1)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new FairyCloak();
    }
}
