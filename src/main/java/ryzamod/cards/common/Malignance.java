package ryzamod.cards.common;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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

public class Malignance extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Malignance",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;

    private static final int UPG_MAGIC = 1;
    public Malignance() {
        super(cardInfo);
        this.exhaust = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    public Malignance(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(magicNumber, false, new ArrayList<>(Arrays.asList(MaterialCategory.POISONS))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Malignance();
    }
}
