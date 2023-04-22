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
            "Gather", //Card ID. Will be prefixed with mod id, so the final ID will be "modID:MyCard" with whatever your mod's ID is.
            0, //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
            CardType.ATTACK, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardTarget.SELF, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            CardRarity.BASIC, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            RyzaCharacter.Enums.CARD_COLOR //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or something similar for a basegame character color.
    );


    //This is theoretically optional, but you'll want it. The ID is how you refer to the card.
    //For example, to add a card to the starting deck, you need to use its ID.
    //With this, you can just use 'MyCard.ID'. Without it, you'd have to type out
    //'yourModID:MyCard' and make sure you don't make any mistakes, and you'd also have to update it
    //if you decided to change the card's ID.
    public static final String ID = makeID(cardInfo.baseId);

    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    private static final int COST = 1;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 2;

    public Gather() {
        super(cardInfo); //Pass the cardInfo to the BaseCard constructor.

        setMagic(MAGIC, UPG_MAGIC);
    }

    public Gather(CardInfo cardInfo) {
        super(cardInfo);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GatherMaterialAction(3, false, new ArrayList<MaterialCategory>(Arrays.asList(
                MaterialCategory.LUMBER,
                MaterialCategory.THREAD,
                MaterialCategory.GUNPOWDER
        ))));
        addToBot(new ApplyPowerAction(p, p, new MindfulnessPower(p, 1)));
    }


    @Override
    public AbstractCard makeCopy() { //Optional
        return new Gather();
    }
}
