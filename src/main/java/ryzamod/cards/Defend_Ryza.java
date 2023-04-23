package ryzamod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.CardInfo;

import static ryzamod.RyzaMod.makeID;

public class Defend_Ryza extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Defend_Ryza",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            RyzaCharacter.Enums.CARD_COLOR
    );
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Defend_Ryza() {
        super(cardInfo);
        this.tags.add(CardTags.STARTER_DEFEND);

        setBlock(BLOCK, UPG_BLOCK);
    }

    public Defend_Ryza(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (Settings.isDebug) {
            this.addToBot(new GainBlockAction(p, p, 50));
        } else {
            this.addToBot(new GainBlockAction(p, p, this.block));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(this.damageUpgrade);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend_Ryza();
    }
}
