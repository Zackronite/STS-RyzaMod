package ryzamod.cards.uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
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
import static ryzamod.cards.materials.MaterialCategory.LUMBER;
import static ryzamod.cards.materials.MaterialCategory.STONE;

public class HunterGatherer extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HunterGatherer",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            RyzaCharacter.Enums.CARD_COLOR
    );


    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public HunterGatherer() {
        super(cardInfo);

        setDamage(DAMAGE, UPG_DAMAGE);
    }

    public HunterGatherer(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new GatherMaterialAction(1,false, new ArrayList<>(Arrays.asList(MaterialCategory.STONE))));
    }

    @Override
    public AbstractCard makeCopy() { //Optional
        return new HunterGatherer();
    }
}
