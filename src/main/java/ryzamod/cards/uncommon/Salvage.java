package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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

public class Salvage extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Salvage",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);


    public Salvage() {
        super(cardInfo);

    }

    public Salvage(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustAction(1, false));
        addToBot(new GatherMaterialAction(1,false, new ArrayList<>(Arrays.asList(MaterialCategory.STONE,
                MaterialCategory.FLOWERS))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Salvage();
    }
}
