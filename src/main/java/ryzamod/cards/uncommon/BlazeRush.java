package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.ElementFirePower;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class BlazeRush extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "BlazeRush",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public BlazeRush() {
        super(cardInfo);

        this.exhaust = true;
        setMagic(MAGIC, UPG_MAGIC);
    }

    public BlazeRush(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower fireElement = AbstractDungeon.player.getPower(RyzaMod.makeID("ElementFire"));
        if (fireElement != null) {
            int fire = fireElement.amount;
            addToBot(new ApplyPowerAction(p, p, new ElementFirePower(p, magicNumber * fire)));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BlazeRush();
    }
}
