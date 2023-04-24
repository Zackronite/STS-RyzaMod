package ryzamod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import ryzamod.RyzaMod;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;

public class GatheringBasket extends BaseRelic {
    // ID, images, text.
    public static final String NAME = "GatheringBasket";
    public static final String ID = RyzaMod.makeID("GatheringBasket");
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public GatheringBasket() {
        super(ID, NAME, RyzaCharacter.Enums.CARD_COLOR, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.flash();
        addToBot(new GatherMaterialAction(1, true, MaterialCategory.getCategoriesOfRarity(0)));
    }
    public AbstractRelic makeCopy() {
        return new GatheringBasket();
    }

}