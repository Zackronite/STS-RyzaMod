package ryzamod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter.Enums;
import ryzamod.util.CardInfo;

public class ShiningStrike extends BaseCard {
    private static final CardInfo cardInfo = new CardInfo(
            "ShiningStrike",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            Enums.CARD_COLOR
    );
    public static final String ID = RyzaMod.makeID(cardInfo.baseId);
    private static final int DAMAGE = 9;
    private static final int CHAIN_EFFECT = 5;
    private static final int UPG_DAMAGE = 3;

    public ShiningStrike() {
        super(cardInfo);
        this.setDamage(DAMAGE, UPG_DAMAGE);
        this.setMagic(CHAIN_EFFECT);
    }

    public ShiningStrike(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if (AbstractDungeon.player.hasPower(RyzaMod.makeID("Tactics Level")) && AbstractDungeon.player.getPower(RyzaMod.makeID("Tactics Level")).amount >= 2) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage += this.magicNumber;
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }

    }

    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(RyzaMod.makeID("Tactics Level")) && AbstractDungeon.player.getPower(RyzaMod.makeID("Tactics Level")).amount >= 2) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage += this.magicNumber;
            super.applyPowers();
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageType.NORMAL), AttackEffect.SLASH_VERTICAL));
    }

    public AbstractCard makeCopy() {
        return new ShiningStrike();
    }
}
