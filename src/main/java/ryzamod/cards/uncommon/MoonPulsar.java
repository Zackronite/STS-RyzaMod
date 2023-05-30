package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.GenericWhirlwindAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class MoonPulsar extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MoonPulsar",
            1,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DMG = 4;
    private static final int NUM_TIMES = 3;
    private static final int UPG_NUM_TIMES = 1;

    public MoonPulsar() {
        super(cardInfo);
        this.isMultiDamage = true;
        setDamage(DMG);
        setMagic(NUM_TIMES, UPG_NUM_TIMES);
    }

    public MoonPulsar(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GenericWhirlwindAction(p, this.multiDamage, this.damageType, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new MoonPulsar();
    }
}
