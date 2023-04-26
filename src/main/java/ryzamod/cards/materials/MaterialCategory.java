package ryzamod.cards.materials;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public enum MaterialCategory {
    GUNPOWDER("Gunpowder", 0),
    THREAD("Thread", 0),
    LUMBER("Lumber", 0),
    FLOWERS("Flower", 1),
    POISONS("Poisons", 1),
    STONE("Stone", 1),
    MAGICAL("Magical", 2);

    private final String name;
    private final int rarity;

    MaterialCategory(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public AbstractCard.CardRarity getCardRarity() {
        switch (this.rarity) {
            case 2:
                return AbstractCard.CardRarity.RARE;
            case 1:
                return AbstractCard.CardRarity.UNCOMMON;
            case 0:
            default:
                return AbstractCard.CardRarity.COMMON;
        }
    }

    public int getRarity() {
        return this.rarity;
    }

    public String getName() {
        return this.name;
    }

    public static ArrayList<MaterialCategory> getCategoriesOfRarity(int r) {
        ArrayList<MaterialCategory> categories = new ArrayList<>();
        for (MaterialCategory mat : MaterialCategory.values()) {
            if (r == -1 || mat.rarity == r) {
                categories.add(mat);
            }
        }

        return categories;
    }

    public static ArrayList<MaterialCategory> getCategoriesOfRarity(int[] rs) {
        ArrayList<MaterialCategory> categories = new ArrayList<>();
        for (int i : rs) {
            categories.addAll(getCategoriesOfRarity(i));
        }

        return categories;
    }
}
