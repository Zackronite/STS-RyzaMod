package ryzamod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.ChangeChainAction;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Ambush extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Ambush",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 4;

    public Ambush() {
        super(cardInfo);
        this.isInnate = true;
        this.exhaust = true;
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public Ambush(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Ambush();
    }
}
