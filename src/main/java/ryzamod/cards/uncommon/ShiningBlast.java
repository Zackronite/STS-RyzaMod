package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ElementLightningPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class ShiningBlast extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ShiningBlast",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int LIGHTNING_AMT = 1;

    public ShiningBlast() {
        super(cardInfo);
        setMagic(LIGHTNING_AMT);
        this.upgInnate = true;
        this.exhaust = true;
    }

    public ShiningBlast(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amountToGain = this.magicNumber * AbstractDungeon.getMonsters().monsters.size();
        addToBot(new ApplyPowerAction(p, p, new ElementLightningPower(p, amountToGain)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new ShiningBlast();
    }
}
