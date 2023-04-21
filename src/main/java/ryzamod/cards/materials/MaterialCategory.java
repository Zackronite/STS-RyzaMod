package ryzamod.cards.materials;

import com.megacrit.cardcrawl.cards.AbstractCard;

public enum MaterialCategory {
    GUNPOWDER("Gunpowder", 0),
    THREAD("Thread", 0),
    LUMBER("Lumber", 0),
    FLOWERS("Flowers", 1),
    POISONS("Poisons", 1),
    STONE("Stone", 1),
    MAGICAL("Magical", 2);

    private final String name;
    // 0 for common (gunpowder, lumber, thread), 1 for uncommon (poisons, flowers, stone), 2 for rare (magical)
    private final int rarity;

    MaterialCategory(String name, int rarity) {
        this.name = name;
        this.rarity = rarity;
    }

    public AbstractCard.CardRarity getRarity() {
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

    public String getName() {
        return this.name;
    }
}
