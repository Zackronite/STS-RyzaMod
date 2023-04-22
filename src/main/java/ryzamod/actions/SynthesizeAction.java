package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import ryzamod.cards.common.*;
import ryzamod.cards.materials.MaterialCard;
import ryzamod.cards.materials.MaterialCategory;
import ryzamod.character.RyzaCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SynthesizeAction extends AbstractGameAction {
    public static final int MAX_NUM_MATERIALS = 4;
    public static final HashMap<ArrayList<MaterialCategory>, AbstractCard> recipes;

    private boolean didSynthesize;
    public SynthesizeAction() {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.EXHAUST;
        this.amount = MAX_NUM_MATERIALS;
        this.didSynthesize = false;
    }
    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (RyzaCharacter.materials.size() == 0) {
                this.isDone = true;
                return;
            }

            AbstractDungeon.gridSelectScreen.open(RyzaCharacter.materials, this.amount, true, "Some text");
            this.tickDuration();
            return;
        }

        if (AbstractDungeon.gridSelectScreen.selectedCards.size() > 0 && !this.didSynthesize) {
            ArrayList<MaterialCategory> ingredients = new ArrayList<>();

            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                ingredients.add(((MaterialCard)card).category);
            }
            for (AbstractCard card : AbstractDungeon.gridSelectScreen.selectedCards) {
                RyzaCharacter.materials.removeCard(card);
                AbstractDungeon.effectList.add(new ExhaustCardEffect(card));
            }

            if (recipes.containsKey(ingredients)) {
                addToBot(new MakeTempCardInHandAction(recipes.get(ingredients)));
                addToBot(new SFXAction("ORB_SLOT_GAIN"));
                this.didSynthesize = true;
            }
        }

        this.tickDuration();
    }

    static {
        recipes = new HashMap<>();
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER)), new Plajig());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER, MaterialCategory.LUMBER)), new Luft());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.LUMBER, MaterialCategory.GUNPOWDER)), new Luft());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER, MaterialCategory.STONE)), new GrandBomb());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE, MaterialCategory.GUNPOWDER)), new GrandBomb());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE,
                MaterialCategory.GUNPOWDER, MaterialCategory.GUNPOWDER)), new NA());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER,
                MaterialCategory.STONE, MaterialCategory.GUNPOWDER)), new NA());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.GUNPOWDER,
                MaterialCategory.GUNPOWDER, MaterialCategory.STONE)), new NA());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL,
                MaterialCategory.LUMBER, MaterialCategory.STONE, MaterialCategory.GUNPOWDER)), new Apocalypse());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE,
                MaterialCategory.STONE, MaterialCategory.STONE)), new GenesisHammer());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE, MaterialCategory.STONE)), new AstronomicalClock());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE)), new MigratoryCharm());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.FLOWERS)), new Nectar());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.FLOWERS, MaterialCategory.MAGICAL)), new Elixir());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL, MaterialCategory.FLOWERS)), new Elixir());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.POISONS)), new WitchsPotion());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.POISONS, MaterialCategory.POISONS)), new DemonicPotion());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.LUMBER)), new HandmadeStaff());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.LUMBER,
                MaterialCategory.STONE, MaterialCategory.MAGICAL)), new SparklingReverie());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.THREAD)), new TravelersCoat());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.THREAD, MaterialCategory.STONE)), new FortressArmor());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE, MaterialCategory.THREAD)), new FortressArmor());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.THREAD,
                MaterialCategory.MAGICAL, MaterialCategory.MAGICAL)), new FairyCloak());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL,
                MaterialCategory.THREAD, MaterialCategory.MAGICAL)), new FairyCloak());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL,
                MaterialCategory.MAGICAL, MaterialCategory.THREAD)), new FairyCloak());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.MAGICAL, MaterialCategory.STONE)), new ManaLantern());
        recipes.put(new ArrayList<>(Arrays.asList(MaterialCategory.STONE, MaterialCategory.MAGICAL)), new ManaLantern());
    }
}
