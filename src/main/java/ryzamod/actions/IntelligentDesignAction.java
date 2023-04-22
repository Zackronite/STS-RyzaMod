package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import ryzamod.cards.crafts.CraftCard;

public class IntelligentDesignAction extends AbstractGameAction {
    private AbstractPlayer p;

    public IntelligentDesignAction() {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        CardGroup exhaustPileOnlyCrafts = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

        for (AbstractCard card : this.p.exhaustPile.group) {
            if (card instanceof CraftCard) {
                exhaustPileOnlyCrafts.addToTop(card);
            }
        }

        if (exhaustPileOnlyCrafts.isEmpty()) {
            this.isDone = true;
            return;
        } else {
            for (AbstractCard card : exhaustPileOnlyCrafts.group) {
                if (this.p.hand.size() == 10) {
                    this.p.discardPile.addToTop(card);
                } else {
                    // this.p.hand.addToHand(card);
                    addToBot(new MakeTempCardInHandAction(card));
                }
                this.p.exhaustPile.removeCard(card);
            }

            this.p.hand.refreshHandLayout();
        }

        this.isDone = true;
    }
}
