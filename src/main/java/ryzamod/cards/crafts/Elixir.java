package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class Elixir extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Elixir",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 5;

    public Elixir() {
        super(cardInfo);

        setMagic(MAGIC);
    }

    public Elixir(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Elixir();
    }
}
