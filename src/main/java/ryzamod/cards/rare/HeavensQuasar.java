package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ElementWindPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class HeavensQuasar extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HeavensQuasar",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DMG = 15;
    private static final int DMG_UPG = 5;
    private static final int WIND_AMT = 3;

    public HeavensQuasar() {
        super(cardInfo);
        setDamage(DMG, DMG_UPG);
        setMagic(WIND_AMT);
    }

    public HeavensQuasar(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new ApplyPowerAction(p, p, new ElementWindPower(p, this.magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HeavensQuasar();
    }
}
