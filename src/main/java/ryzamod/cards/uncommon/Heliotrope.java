package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.character.RyzaCharacter;
import ryzamod.character.RyzaCharacter.Enums;
import ryzamod.util.CardInfo;

public class Heliotrope extends BaseCard {
    private static final CardInfo cardInfo = new CardInfo("Heliotrope", 1, CardType.SKILL, CardTarget.SELF, CardRarity.UNCOMMON, Enums.CARD_COLOR);
    public static final String ID = RyzaMod.makeID(cardInfo.baseId);

    private static final int DRAW_AMT = 1;

    public Heliotrope() {
        super(cardInfo);
    }

    public Heliotrope(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void applyPowers() {
        super.applyPowers();
        int count = DRAW_AMT * RyzaCharacter.materials.size();

        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + count;
        if (count == 1) {
            this.rawDescription = this.rawDescription + this.cardStrings.EXTENDED_DESCRIPTION[1];
        } else {
            this.rawDescription = this.rawDescription + this.cardStrings.EXTENDED_DESCRIPTION[2];
        }

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(RyzaCharacter.materials.size()));
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new Heliotrope();
    }
}
