package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.util.CardInfo;

import java.util.HashMap;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class AstronomicalClock extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "AstronomicalClock",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int MAGIC = 1;

    public AstronomicalClock() {
        super(cardInfo);
        setMagic(MAGIC);
    }

    public AstronomicalClock(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public HashMap<MaterialCategory, Integer> getRecipe() {
        HashMap<MaterialCategory, Integer> recipe = new HashMap<>();
        recipe.put(MaterialCategory.STONE, 2);
        return recipe;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p,magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p,magicNumber)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new AstronomicalClock();
    }
}
