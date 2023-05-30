package ryzamod.cards.rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.actions.SynthesizeAction;
import ryzamod.cards.BaseCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Coalescence extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Coalescence",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int GATHER_AMT = 2;
    private static final int UPG_GATHER_AMT = 1;

    public Coalescence() {
        super(cardInfo);
        setMagic(GATHER_AMT, UPG_GATHER_AMT);
    }

    public Coalescence(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(this.magicNumber, false, MaterialCategory.getCategoriesOfRarity(new int[]{0, 1})));
        addToBot(new SynthesizeAction());
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Coalescence();
    }
}
