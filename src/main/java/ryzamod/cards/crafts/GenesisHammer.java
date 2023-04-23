package ryzamod.cards.crafts;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class GenesisHammer extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "GenesisHammer",
            0,
            CardType.ATTACK,
            CardTarget.ALL_ENEMY,
            CardRarity.RARE,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 1;

    public GenesisHammer() {
        super(cardInfo);

        setMagic(MAGIC);
    }

    public GenesisHammer(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            AbstractDungeon.actionManager.addToBottom(new StunMonsterAction(mo, p, magicNumber));
        }


        addToBot(new StunMonsterAction(m, p));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new GenesisHammer();
    }
}
