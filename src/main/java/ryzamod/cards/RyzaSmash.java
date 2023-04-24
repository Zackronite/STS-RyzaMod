package ryzamod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class RyzaSmash extends BaseCard{
    private final static CardInfo cardInfo = new CardInfo(
            "RyzaSmash",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            RyzaCharacter.Enums.CARD_COLOR
    );
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 5;

    public RyzaSmash() {
        super(cardInfo);

        setMagic(DAMAGE, UPG_DAMAGE);
    }

    public RyzaSmash(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        this.baseDamage = this.magicNumber * AbstractDungeon.player.getPower(makeID("Tactics Level")).amount;
        super.calculateCardDamage(mo);
        this.baseDamage = DAMAGE;
        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + this.cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void applyPowers() {
        this.baseDamage = this.magicNumber * AbstractDungeon.player.getPower(makeID("Tactics Level")).amount;
        super.applyPowers();
        this.baseDamage = DAMAGE;
        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + this.cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_DAMAGE);
            this.initializeDescription();
        }
    }

    public void onMoveToDiscard() {
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new RyzaSmash();
    }
}
