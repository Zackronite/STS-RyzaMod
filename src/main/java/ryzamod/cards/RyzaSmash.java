package ryzamod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.TacticsLevelPower;
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

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public RyzaSmash(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int tacticsLevel = AbstractDungeon.player.getPower(RyzaMod.makeID("Tactics Level")).amount;
        addToBot(new DamageAction(m, new DamageInfo(p, (damage * tacticsLevel), DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RyzaSmash();
    }
}
