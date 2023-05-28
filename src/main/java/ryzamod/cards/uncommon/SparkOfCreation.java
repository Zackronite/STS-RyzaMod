package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CreativityPower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class SparkOfCreation extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SparkOfCreation",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );

    public static final String ID = makeID(cardInfo.baseId);

    public SparkOfCreation() {
        super(cardInfo);
    }

    public SparkOfCreation(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CreativityPower(p, 3)));
        RyzaCharacter.maxNumMaterials -= 1;
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new SparkOfCreation();
    }
}
