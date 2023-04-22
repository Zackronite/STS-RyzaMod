package ryzamod.cards.materials;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.util.CardInfo;

import java.util.HashMap;

public abstract class MaterialCard extends BaseCard {
    public static String ID;
    public HashMap<ElementType, Integer> elementValues;
    public MaterialCategory category;

    public MaterialCard(String materialName, MaterialCategory materialCategory, int fire, int ice, int lightning, int wind) {
        // TODO: Change card type from status to material
        super(new CardInfo(materialName, -2, CardType.STATUS, CardTarget.NONE, materialCategory.getCardRarity(), CardColor.COLORLESS));
        ID = RyzaMod.makeID(materialName);
        category = materialCategory;
        elementValues = new HashMap<>();
        elementValues.put(ElementType.FIRE, fire);
        elementValues.put(ElementType.ICE, ice);
        elementValues.put(ElementType.LIGHTNING, lightning);
        elementValues.put(ElementType.WIND, wind);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        // will never be used by materials (probably)
    }

    @Override
    public int compareTo(AbstractCard other) {
        if (other instanceof MaterialCard) {
            return this.category.getRarity() - ((MaterialCard)other).category.getRarity();
        }
        return 1;
    }
}
