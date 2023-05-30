package ryzamod.cards.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.GenericWhirlwindAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class StrategicOnslaught extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StrategicOnslaught",
            0,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DMG = 10;

    public StrategicOnslaught() {
        super(cardInfo);
        setDamage(DMG);
        this.isMultiDamage = true;
        this.exhaust = true;
    }

    public StrategicOnslaught(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GenericWhirlwindAction(p, this.multiDamage, this.damageType, p.getPower(makeID("Tactics Level")).amount));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StrategicOnslaught();
    }
}
