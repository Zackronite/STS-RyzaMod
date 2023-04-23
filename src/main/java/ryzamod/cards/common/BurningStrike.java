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

public class BurningStrike extends BaseCard {
    private static final CardInfo cardInfo = new CardInfo(
            "BurningStrike",
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

    public BurningStrike() {
        super(cardInfo);
        this.setDamage(9, 3);
        this.setMagic(5);
    }

    public BurningStrike(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        if (AbstractDungeon.player.hasPower(RyzaMod.makeID("Chain")) && AbstractDungeon.player.getPower(RyzaMod.makeID("Chain")).amount >= 2) {
            int realBaseDamage = this.baseDamage;
            this.baseDamage += this.magicNumber;
            super.calculateCardDamage(mo);
            this.baseDamage = realBaseDamage;
            this.isDamageModified = this.damage != this.baseDamage;
        }

    }

    public void applyPowers() {
        if (AbstractDungeon.player.hasPower(RyzaMod.makeID("Chain")) && AbstractDungeon.player.getPower(RyzaMod.makeID("Chain")).amount >= 2) {
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
        return new BurningStrike();
    }
}
