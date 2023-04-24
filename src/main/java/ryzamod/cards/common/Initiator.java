package ryzamod.cards.common;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.LoseDexterityPower;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Initiator extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Initiator",
            0,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 2;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public Initiator() {
        super(cardInfo);

        this.isInnate = true;
        this.exhaust = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    public Initiator(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseStrengthPower(p, magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new LoseDexterityPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Initiator();
    }
}
