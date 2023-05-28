package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.cards.uncommon.SparkOfCreation;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CreativityPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class LapseInJudgment extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "LapseInJudgment",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 20;

    public LapseInJudgment() {
        super(cardInfo);
        setDamage(DAMAGE);
    }

    public LapseInJudgment(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new ApplyPowerAction(p, p, new CreativityPower(p, -2)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new LapseInJudgment();
    }
}
