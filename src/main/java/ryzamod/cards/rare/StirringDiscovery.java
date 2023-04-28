package ryzamod.cards.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.BaseCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static ryzamod.RyzaMod.makeID;

public class StirringDiscovery extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "StirringDiscovery",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    public StirringDiscovery() {
        super(cardInfo);
    }

    public StirringDiscovery(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(1, false, new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new StirringDiscovery();
    }
}
