package ryzamod.cards.common;

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

public class Scrounge extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Scrounge",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);



    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public Scrounge() {
        super(cardInfo);
        this.exhaust = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    public Scrounge(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(1, false, new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER,
                MaterialCategory.THREAD, MaterialCategory.LUMBER))));
    }
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.exhaust = false;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }

    }
    @Override
    public AbstractCard makeCopy() { //Optional
        return new Scrounge();
    }
}
