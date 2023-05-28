package ryzamod.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.RyzaMod;
import ryzamod.cards.BaseCard;
import ryzamod.cards.uncommon.Heliotrope;
import ryzamod.character.RyzaCharacter;
import ryzamod.powers.CreativityPower;
import ryzamod.util.CardInfo;

public class AlternateApproach extends BaseCard {
    private static final CardInfo cardInfo = new CardInfo("AlternateApproach", 2, CardType.SKILL, CardTarget.SELF, CardRarity.RARE, RyzaCharacter.Enums.CARD_COLOR);
    public static final String ID = RyzaMod.makeID(cardInfo.baseId);

    private static final int CREATIVITY_AMT = 1;

    public AlternateApproach() {
        super(cardInfo);
        setMagic(CREATIVITY_AMT);
    }

    public AlternateApproach(CardInfo cardInfo) {
        super(cardInfo);
    }

    public void applyPowers() {
        super.applyPowers();
        int count = CREATIVITY_AMT * RyzaCharacter.materials.size();

        this.rawDescription = this.cardStrings.DESCRIPTION + this.cardStrings.EXTENDED_DESCRIPTION[0] + count + this.cardStrings.EXTENDED_DESCRIPTION[1];

        this.initializeDescription();
    }

    public void onMoveToDiscard() {
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new CreativityPower(p, this.magicNumber * RyzaCharacter.materials.size())));
        RyzaCharacter.materials.clear();
        this.rawDescription = this.cardStrings.DESCRIPTION;
        this.initializeDescription();
    }

    public AbstractCard makeCopy() {
        return new AlternateApproach();
    }
}
