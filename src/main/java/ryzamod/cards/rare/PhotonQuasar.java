package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class PhotonQuasar extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "PhotonQuasar",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public PhotonQuasar() {
        super(cardInfo);

        setMagic(MAGIC, UPG_MAGIC);
        this.exhaust = true;
    }

    public PhotonQuasar(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        this.addToBot(new MakeTempCardInDiscardAction(new VoidCard(), 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new PhotonQuasar();
    }
}
