package ryzamod.cards.tempCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class Star extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Star",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.SPECIAL,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);

    public static final int DAMAGE = 4;
    public static final int UPG_DAMAGE = 2;

    public Star() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
        this.exhaust = true;
    }

    public Star(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Star();
    }
}
