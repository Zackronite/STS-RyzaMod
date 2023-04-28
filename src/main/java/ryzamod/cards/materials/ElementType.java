package ryzamod.cards.materials;

public enum ElementType {
    FIRE("Fire"),
    ICE("Ice"),
    LIGHTNING("Lightning"),
    WIND("Wind");

    private String name;

    ElementType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
