package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.BaseCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static ryzamod.RyzaMod.makeID;

public class CrystallizingAura extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CrystallizingAura",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int WEAK_AMT = 99;

    public CrystallizingAura() {
        super(cardInfo);
        setMagic(WEAK_AMT);
        setCostUpgrade(0);
        this.exhaust = true;
    }

    public CrystallizingAura(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(p, this.magicNumber, false)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new CrystallizingAura();
    }
}
