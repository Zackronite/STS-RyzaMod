package ryzamod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BurningBlood;
import ryzamod.RyzaMod;
import ryzamod.actions.GatherMaterialAction;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.cards.materials.gunpowder.MagmaPowder;
import ryzamod.cards.materials.lumber.MossyDriftwood;
import ryzamod.cards.materials.thread.FluffyWool;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.TextureLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static ryzamod.RyzaMod.relicPath;

public class GatheringBasket extends BaseRelic {

    /*
     * https://github.com/daviscook477/BaseMod/wiki/Custom-Relics
     *
     * Gain 1 energy.
     */

    // ID, images, text.

    public static final String NAME = "GatheringBasket";
    public static final String ID = RyzaMod.makeID("GatheringBasket");
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public GatheringBasket() {
        super(ID, NAME, RyzaCharacter.Enums.CARD_COLOR, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    // Flash at the start of Battle.
    @Override
    public void atBattleStart() {
        this.flash();
        addToBot(new GatherMaterialAction(1, true, MaterialCategory.getCategoriesOfRarity(0)));
    }
    public AbstractRelic makeCopy() {
        return new GatheringBasket();
    }

}