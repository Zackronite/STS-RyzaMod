package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class CascadingStar extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CascadingStar",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 2;

    private static final int UPG_MAGIC = 1;

    public CascadingStar() {
        super(cardInfo);

        setMagic(MAGIC, UPG_MAGIC);
    }

    public CascadingStar(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void calculateCardDamage(AbstractMonster mo) {
        int total = 0;
        AbstractPower fireElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementFire"));
        if (fireElement != null) {
            total = fireElement.amount;
        }
        AbstractPower iceElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementIce"));
        if (iceElement != null) {
            total += iceElement.amount;
        }
        AbstractPower windElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementWind"));
        if (windElement != null) {
            total += windElement.amount;
        }
        AbstractPower lightningElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementLightning"));
        if (lightningElement != null) {
            total += lightningElement.amount;
        }
        this.baseDamage = this.magicNumber * total;
        super.calculateCardDamage(mo);
        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + this.cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void applyPowers() {
        int total = 0;
        AbstractPower fireElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementFire"));
        if (fireElement != null) {
            total = fireElement.amount;
        }
        AbstractPower iceElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementIce"));
        if (iceElement != null) {
            total += iceElement.amount;
        }
        AbstractPower windElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementWind"));
        if (windElement != null) {
            total += windElement.amount;
        }
        AbstractPower lightningElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementLightning"));
        if (lightningElement != null) {
            total += lightningElement.amount;
        }
        this.baseDamage = this.magicNumber * total;
        super.applyPowers();
        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + this.damage + this.cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPG_MAGIC);
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
    public AbstractCard makeCopy() { //Optional
        return new CascadingStar();
    }
}
