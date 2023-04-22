package ryzamod.cards.crafts;

import ryzamod.cards.BaseCard;
import ryzamod.util.CardInfo;

public abstract class CraftCard extends BaseCard {
    public CraftCard(CardInfo cardInfo) {
        super(cardInfo);
        this.exhaust = true;
    }
}
