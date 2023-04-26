package ryzamod.cards.materials;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.random.Random;
import ryzamod.cards.crafts.*;
import ryzamod.cards.materials.flowers.LuckyClover;
import ryzamod.cards.materials.gunpowder.MagmaPowder;
import ryzamod.cards.materials.lumber.MossyDriftwood;
import ryzamod.cards.materials.magical.HeavenlyString;
import ryzamod.cards.materials.poisons.TemptingSap;
import ryzamod.cards.materials.stone.Arknite;
import ryzamod.cards.materials.thread.FluffyWool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class MaterialLibrary {
    public static int totalMaterialCount;
    public static int totalCraftCount;
    public static int commonMaterials;
    public static int uncommonMaterials;
    public static int rareMaterials;
    public static HashMap<String, MaterialCard> materials = new HashMap<>();
    public static HashMap<String, CraftCard> crafts = new HashMap<>();

    public MaterialLibrary() {}

    private static void initialize() {
        materials.clear();
        addCommonMaterials();
        addUncommonMaterials();
        addRareMaterials();
        addCrafts();
    }

    private static void add(AbstractCard c) {
        if (c instanceof MaterialCard) {
            switch (c.rarity) {
                case COMMON:
                    commonMaterials++;
                    break;
                case UNCOMMON:
                    uncommonMaterials++;
                    break;
                case RARE:
                    rareMaterials++;
                    break;
            }

            materials.put(c.cardID, (MaterialCard) c);
            totalMaterialCount++;
        } else if (c instanceof CraftCard) {
            crafts.put(c.cardID, (CraftCard) c);
            totalCraftCount++;
        }
    }

    // gunpowder, thread, lumber
    private static void addCommonMaterials() {
        add(new FluffyWool());
        add(new MagmaPowder());
        add(new MossyDriftwood());
    }

    // flowers, poisons, stone
    private static void addUncommonMaterials() {
        add(new LuckyClover());
        add(new TemptingSap());
        add (new Arknite());
    }

    // magical
    private static void addRareMaterials() {
        add(new HeavenlyString());
    }

    private static void addCrafts() {
        // common
        add(new HandmadeStaff());
        add(new Plajig());
        add(new Luft());
        add(new TravelersCoat());
        add(new WitchsPotion());
        add(new Nectar());
        add(new MigratoryCharm());

        // uncommon
        add(new GrandBomb());
        add(new FortressArmor());
        add(new AstronomicalClock());
        add(new DemonicPotion());
        add(new Elixir());
        add(new ManaLantern());

        // rare
        add(new SparklingReverie());
        add(new NA());
        add(new FairyCloak());
        add(new GenesisHammer());
        add(new Apocalypse());
    }

    public static ArrayList<AbstractCard> getAllCrafts() {
        ArrayList<AbstractCard> craftList = new ArrayList<>();
        for (CraftCard craft : crafts.values()) {
            craftList.add(craft.makeCopy());
        }

        Collections.sort(craftList);

        return craftList;
    }

    public static MaterialCard getMaterial(String key) {
        return (MaterialCard) materials.get(key).makeCopy();
    }

    public static ArrayList<AbstractCard> getAllMaterialsOfRarity(int rarity) {
        ArrayList<AbstractCard> materialList = new ArrayList<>();
        for (MaterialCard mat : materials.values()) {
            if (mat.category.getRarity() == rarity) {
                materialList.add(mat.makeCopy());
            }
        }

        return materialList;
    }

    public static ArrayList<AbstractCard> getAllMaterialsOfRarity(int[] rarity) {
        ArrayList<AbstractCard> materialList = new ArrayList<>();
        for (MaterialCard mat : materials.values()) {
            for (int r : rarity) {
                if (mat.category.getRarity() == r) {
                    materialList.add(mat.makeCopy());
                }
            }
        }

        return materialList;
    }

    public static ArrayList<AbstractCard> getAllMaterialsOfCategory(MaterialCategory category) {
        ArrayList<AbstractCard> materialList = new ArrayList<>();
        for (MaterialCard mat : materials.values()) {
            if (mat.category.equals(category)) {
                materialList.add(mat.makeCopy());
            }
        }

        return materialList;
    }

    public static ArrayList<AbstractCard> getAllMaterialsOfCategory(ArrayList<MaterialCategory> category) {
        ArrayList<AbstractCard> materialList = new ArrayList<>();
        for (MaterialCard mat : materials.values()) {
            if (category.contains(mat.category)) {
                materialList.add(mat.makeCopy());
            }
        }

        return materialList;
    }

    public static AbstractCard getRandomMaterial() {
        Random r = new Random();
        ArrayList<AbstractCard> materialList = new ArrayList<>(materials.values());

        return materialList.get(r.random(materialList.size() - 1)).makeCopy();
    }

    public static AbstractCard getRandomMaterial(ArrayList<MaterialCategory> category) {
        Random r = new Random();
        ArrayList<AbstractCard> materialList = getAllMaterialsOfCategory(category);

        return materialList.get(r.random(materialList.size() - 1)).makeCopy();
    }

    static {
        initialize();
    }
}
