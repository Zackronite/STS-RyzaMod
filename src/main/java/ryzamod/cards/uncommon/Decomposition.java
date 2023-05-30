package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

public class Decomposition extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Decomposition",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );
    public static final String ID = RyzaMod.makeID(cardInfo.baseId);

    private static final int DMG = 10;
    private static final int VULN_AMT = 1;
    private static final int VULN_UPG_AMT = 1;

    public Decomposition() {
        super(cardInfo);
        setDamage(10);
        setMagic(VULN_AMT, VULN_UPG_AMT);
    }

    public Decomposition(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void applyPowers() {
        super.applyPowers();
        int count = this.magicNumber * AbstractDungeon.player.getPower(RyzaMod.makeID("Tactics Level")).amount;

        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + count + this.cardStrings.EXTENDED_DESCRIPTION[1];

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber * AbstractDungeon.player.getPower(RyzaMod.makeID("Tactics Level")).amount, false)));
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new Decomposition();
    }
}
