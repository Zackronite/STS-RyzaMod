package ryzamod.vfx;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;

public class ShowMaterialEffect extends ShowCardBrieflyEffect {
    public ShowMaterialEffect(AbstractCard card, float x, float y) {
        super(card, x, y);
        this.startingDuration = 1.75f;
        this.duration = 1.75f;
    }
}
