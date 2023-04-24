package ryzamod.cards.common;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.CoreRecycleAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class CoreRecycle extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CoreRecycle",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 15;
    private static final int UPG_DAMAGE = 5;

    public CoreRecycle() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public CoreRecycle(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new CoreRecycleAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 2));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CoreRecycle();
    }
}
