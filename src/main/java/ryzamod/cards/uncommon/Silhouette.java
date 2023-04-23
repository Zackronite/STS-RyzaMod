package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CreativityPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Silhouette extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Silhouette",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);
    private static final int CREATIVITY_AMT = 1;
    private static final int UPG_AMT = 1;

    public Silhouette() {
        super(cardInfo);

        setMagic(CREATIVITY_AMT, UPG_AMT);
    }

    public Silhouette(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CreativityPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(this.magicUpgrade);
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Silhouette();
    }
}
