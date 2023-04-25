package ryzamod.screens;

import basemod.abstracts.CustomScreen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBarListener;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;

import java.util.ArrayList;

public class MaterialBagScreen extends CustomScreen implements ScrollBarListener {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public boolean isHovered = false;
    private static final int CARDS_PER_LINE = 5;
    private boolean grabbedScreen = false;
    private static float drawStartX;
    private static float drawStartY;
    private static float padX;
    private static float padY;
    private static final float SCROLL_BAR_THRESHOLD;
    private float scrollLowerBound;
    private float scrollUpperBound;
    private float grabStartY;
    private float currentDiffY;
    private static final String HEADER_INFO;
    private AbstractCard hoveredCard;
    private int prevBagSize;
    private ScrollBar scrollBar;

    public static class Enums {
        @SpireEnum
        public static AbstractDungeon.CurrentScreen MATERIAL_BAG;
    }

    public MaterialBagScreen() {
        // maybe add controller support? probably not because im lazy
        this.scrollLowerBound = -Settings.DEFAULT_SCROLL_LIMIT;
        this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        this.grabStartY = this.scrollLowerBound;
        this.currentDiffY = this.scrollLowerBound;
        this.hoveredCard = null;
        this.prevBagSize = 0;

        drawStartX = (float)Settings.WIDTH;
        drawStartX -= 5f * AbstractCard.IMG_WIDTH * 0.75f;
        drawStartX -= 4f * Settings.CARD_VIEW_PAD_X;
        drawStartX /= 2f;
        drawStartX += AbstractCard.IMG_WIDTH * 0.75f / 2f;

        padX = AbstractCard.IMG_WIDTH * 0.75f + Settings.CARD_VIEW_PAD_X;
        padY = AbstractCard.IMG_HEIGHT * 0.75f + Settings.CARD_VIEW_PAD_Y;
        this.scrollBar = new ScrollBar(this);
        this.scrollBar.changeHeight((float)Settings.HEIGHT - 384f * Settings.scale);
    }

    @Override
    public AbstractDungeon.CurrentScreen curScreen() {
        return Enums.MATERIAL_BAG;
    }

    private void open() {
        CardCrawlGame.sound.play("DECK_OPEN");
        AbstractDungeon.overlayMenu.showBlackScreen();
        this.currentDiffY = this.scrollLowerBound;
        this.grabStartY = this.scrollLowerBound;
        this.grabbedScreen = false;
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.screen = curScreen();

        for (AbstractCard c : RyzaCharacter.materials.group) {
            c.setAngle(0f, true);
            c.targetDrawScale = 0.75f;
            c.drawScale = 0.75f;
            c.lighten(true);
        }

        this.hideCards();
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
        if (RyzaCharacter.materials.group.size() <= 5) {
            drawStartY = (float)Settings.HEIGHT * 0.5f;
        } else {
            drawStartY = (float)Settings.HEIGHT * 0.66f;
        }

        this.calculateScrollBounds();
    }

    @Override
    public void reopen() {
        AbstractDungeon.screen = curScreen();
        AbstractDungeon.isScreenUp = true;
        AbstractDungeon.overlayMenu.cancelButton.show(TEXT[1]);
    }

    @Override
    public void close() {
        AbstractDungeon.overlayMenu.hideBlackScreen();
        if (AbstractDungeon.previousScreen == curScreen()) {
            AbstractDungeon.previousScreen = null;
        }
        AbstractDungeon.isScreenUp = false;
        AbstractDungeon.overlayMenu.cancelButton.hide();
        CardCrawlGame.sound.play("DECK_CLOSE");
    }

    @Override
    public void update() {
        boolean isDraggingScrollBar = false;
        if (this.shouldShowScrollBar()) {
            isDraggingScrollBar = this.scrollBar.update();
        }

        if (!isDraggingScrollBar) {
            this.updateScrolling();
        }

        this.updatePositions();
    }

    @Override
    public void openingSettings() {
        AbstractDungeon.previousScreen = curScreen();
    }

    private void updatePositions() {
        this.hoveredCard = null;
        int lineNum = 0;
        ArrayList<AbstractCard> bag = RyzaCharacter.materials.group;

        for (int i = 0; i < bag.size(); i++) {
            int mod = i % 5;
            if (mod == 0 && i != 0) {
                lineNum++;
            }

            AbstractCard mat = bag.get(i);

            mat.target_x = drawStartX + (float)mod * padX;
            mat.target_y = drawStartY + this.currentDiffY - (float)lineNum * padY;
            mat.unfadeOut();
            mat.update();
            if (AbstractDungeon.topPanel.potionUi.isHidden) {
                mat.updateHoverLogic();
                if (mat.hb.hovered) {
                    this.hoveredCard = mat;
                }
            }
        }
    }

    private void updateScrolling() {
        int y = InputHelper.mY;
        if (!this.grabbedScreen) {
            if (InputHelper.scrolledDown) {
                this.currentDiffY += Settings.SCROLL_SPEED;
            } else if (InputHelper.scrolledUp) {
                this.currentDiffY -= Settings.SCROLL_SPEED;
            }

            if (InputHelper.justClickedLeft) {
                this.grabbedScreen = true;
                this.grabStartY = (float)y - this.currentDiffY;
            }
        } else if (InputHelper.isMouseDown) {
            this.currentDiffY = (float)y - this.grabStartY;
        } else {
            this.grabbedScreen = false;
        }

        if (this.prevBagSize != RyzaCharacter.materials.size()) {
            this.calculateScrollBounds();
        }

        this.resetScrolling();
        this.updateBarPosition();
    }

    private void calculateScrollBounds() {
        int scrollTmp = 0;
        if (RyzaCharacter.materials.size() > 10) {
            scrollTmp = (RyzaCharacter.materials.size() / 5) - 2;
            if (RyzaCharacter.materials.size() % 5 != 0) {
                scrollTmp++;
            }

            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT + (float)scrollTmp * padY;
        } else {
            this.scrollUpperBound = Settings.DEFAULT_SCROLL_LIMIT;
        }
    }

    private void resetScrolling() {
        if (this.currentDiffY < this.scrollLowerBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollLowerBound);
        } else if (this.currentDiffY > this.scrollUpperBound) {
            this.currentDiffY = MathHelper.scrollSnapLerpSpeed(this.currentDiffY, this.scrollUpperBound);
        }
    }

    private void hideCards() {
        if (AbstractDungeon.player instanceof RyzaCharacter) {
            int lineNum = 0;
            ArrayList<AbstractCard> cards = RyzaCharacter.materials.group;

            for (int i = 0; i < cards.size(); i++) {
                int mod = i % 5;
                if (mod == 0 && i != 0) {
                    lineNum++;
                }

                cards.get(i).current_x = drawStartX + (float)mod * padX;
                cards.get(i).current_y = drawStartY + this.currentDiffY - (float)lineNum * padY - MathUtils.random(100f * Settings.scale, 200f * Settings.scale);
            }
        }
    }

    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player instanceof RyzaCharacter) {
            if (this.shouldShowScrollBar()) {
                this.scrollBar.render(sb);
            }

            if (this.hoveredCard == null) {
                RyzaCharacter.materials.render(sb);
            } else {
                RyzaCharacter.materials.renderExceptOneCard(sb, this.hoveredCard);
                this.hoveredCard.renderHoverShadow(sb);
                this.hoveredCard.render(sb);
                this.hoveredCard.renderCardTip(sb);
            }

            sb.setColor(Color.WHITE);
            FontHelper.renderDeckViewTip(sb, HEADER_INFO, 96f * Settings.scale, Settings.CREAM_COLOR);
            RyzaMod.materialBagPanel.render(sb);
        }
    }

    public void scrolledUsingBar(float newPercent) {
        this.currentDiffY = MathHelper.valueFromPercentBetween(this.scrollLowerBound, this.scrollUpperBound, newPercent);
        this.updateBarPosition();
    }

    private void updateBarPosition() {
        float percent = MathHelper.percentFromValueBetween(this.scrollLowerBound, this.scrollUpperBound, this.currentDiffY);
        this.scrollBar.parentScrolledToPercent(percent);
    }

    private boolean shouldShowScrollBar() {
        return this.scrollUpperBound > SCROLL_BAR_THRESHOLD;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("MaterialBagScreen"));
        TEXT = uiStrings.TEXT;
        SCROLL_BAR_THRESHOLD = 500f * Settings.scale;
        HEADER_INFO = TEXT[0];
    }
}
