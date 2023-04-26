package ryzamod.cards.crafts;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.util.CardInfo;

import java.util.HashMap;

import static ryzamod.RyzaMod.makeID;
import static ryzamod.character.RyzaCharacter.Enums.CARD_COLOR;

public class HandmadeStaff extends CraftCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HandmadeStaff",
            0,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            CARD_COLOR.COLORLESS
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int DAMAGE = 12;

    public HandmadeStaff() {
        super(cardInfo);

        setDamage(DAMAGE);
    }

    public HandmadeStaff(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public HashMap<MaterialCategory, Integer> getRecipe() {
        HashMap<MaterialCategory, Integer> recipe = new HashMap<>();
        recipe.put(MaterialCategory.LUMBER, 1);
        return recipe;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HandmadeStaff();
    }
}
