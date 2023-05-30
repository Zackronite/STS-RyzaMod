package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class SouthernCross extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SouthernCross",
            1,
            AbstractCard.CardType.ATTACK,
            AbstractCard.CardTarget.ENEMY,
            AbstractCard.CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DMG = 8;
    private static final int DMG_UPG = 4;

    public SouthernCross() {
        super(cardInfo);
        setDamage(DMG, DMG_UPG);
    }

    public SouthernCross(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damageToDeal = AbstractDungeon.getMonsters().monsters.size() == 1 ? this.damage * 2 : this.damage;
        addToBot(new DamageAction(m, new DamageInfo(p, damageToDeal, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SouthernCross();
    }
}
