package ryzamod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.flowers.LuckyClover;
import ryzamod.cards.materials.gunpowder.MagmaPowder;
import ryzamod.cards.materials.lumber.MossyDriftwood;
import ryzamod.cards.materials.magical.HeavenlyString;
import ryzamod.cards.materials.poisons.TemptingSap;
import ryzamod.cards.materials.stone.Arknite;
import ryzamod.cards.materials.thread.FluffyWool;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.MindfulnessPower;
import ryzamod.util.CardInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static ryzamod.RyzaMod.makeID;

public class Gather extends BaseCard{
    private final static CardInfo cardInfo = new CardInfo(
            "Gather",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int COST = 1;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public Gather() {
        super(cardInfo);

        setMagic(MAGIC, UPG_MAGIC);
    }

    public Gather(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(3, false));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new Gather();
    }
}
