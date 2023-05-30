package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class CrescentSweep extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CrescentSweep",
            2,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DMG = 21;
    private static final int DMG_UPG = 3;

    public CrescentSweep() {
        super(cardInfo);
        setDamage(DMG, DMG_UPG);
    }

    public CrescentSweep(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int currDamage = this.damage;
        addToBot(new VFXAction(new CleaveEffect(), 0.0f));
        for (AbstractMonster eachMonster : AbstractDungeon.getMonsters().monsters) {
            if (!eachMonster.isDeadOrEscaped()) {
                addToBot(new DamageAction(eachMonster, new DamageInfo(p, currDamage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
                addToBot(new SFXAction("ATTACK_HEAVY"));
                currDamage -= 3;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CrescentSweep();
    }
}
