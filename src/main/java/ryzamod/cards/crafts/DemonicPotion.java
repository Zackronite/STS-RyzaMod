package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.util.CardInfo;

import java.util.HashMap;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class DemonicPotion extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "DemonicPotion",
            0,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 8;

    public DemonicPotion() {
        super(cardInfo);

        setBlock(BLOCK);
    }

    public DemonicPotion(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public HashMap<MaterialCategory, Integer> getRecipe() {
        HashMap<MaterialCategory, Integer> recipe = new HashMap<>();
        recipe.put(MaterialCategory.POISONS, 2);
        return recipe;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new SlowPower(m, 1)));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, 5, false)));
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 5, false)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new DemonicPotion();
    }
}
