package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class WitchsPotion extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "WitchsPotion",
            0,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 8;

    public WitchsPotion() {
        super(cardInfo);

        setBlock(BLOCK);
    }

    public WitchsPotion(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, BLOCK));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 2, false)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 2, false)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new WitchsPotion();
    }
}
