package ryzamod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.BurningBlood;
import ryzamod.RyzaMod;
import ryzamod.cards.materials.gunpowder.MagmaPowder;
import ryzamod.cards.materials.lumber.MossyDriftwood;
import ryzamod.cards.materials.thread.FluffyWool;
import ryzamod.character.RyzaCharacter;
import ryzamod.util.TextureLoader;
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
        Random rand = new Random();
        int upperbound = 3;
        int int_random = rand.nextInt(upperbound);
        if (int_random == 0) {
            RyzaCharacter.materials.addToTop(new MagmaPowder());
            this.addToBot(new SFXAction("TINGSHA"));
        }
        if (int_random == 1) {
            RyzaCharacter.materials.addToTop(new MossyDriftwood());
            this.addToBot(new SFXAction("TINGSHA"));
        }
        if (int_random == 2) {
            RyzaCharacter.materials.addToTop(new FluffyWool());
            this.addToBot(new SFXAction("TINGSHA"));
        }
    }
    public AbstractRelic makeCopy() {
        return new GatheringBasket();
    }

}