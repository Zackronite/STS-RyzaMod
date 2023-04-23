package ryzamod.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.BaseCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.lumber.MossyDriftwood;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import java.util.ArrayList;
import java.util.Arrays;

import static ryzamod.RyzaMod.makeID;

public class TakeShelter extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "TakeShelter",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);


    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public TakeShelter() {
        super(cardInfo);
        setBlock(BLOCK, UPG_BLOCK);
    }

    public TakeShelter(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        addToBot(new GatherMaterialAction(1, false, new ArrayList<>(Arrays.asList(MaterialCategory.LUMBER))));
        addToBot(new SFXAction("TINGSHA"));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new TakeShelter();
    }
}
