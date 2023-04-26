package ryzamod.ui;

import basemod.BaseMod;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.TutorialStrings;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;
import ryzamod.screens.RecipesScreen;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;

public class RecipesTopPanelItem extends TopPanelItem {
    private static final Texture IMG = new Texture(RyzaMod.resourcePath("ui/recipes.png"));
    public static final String ID = RyzaMod.makeID("RecipesButton");

    private static final TutorialStrings tutorialStrings;
    public static final String[] MSG;
    public static final String[] LABEL;
    // private static final UIStrings uiStrings;
    // public static final String[] TEXT;
    private static final float TIP_X;
    private static final float TIP_Y;

    // private final ArrayList<CurrentScreen> validScreens;
    private final ArrayList<CurrentScreen> invalidPrevScreens;
    private boolean justHovered;

    public RecipesTopPanelItem() {
        super(IMG, ID);

        invalidPrevScreens = new ArrayList<>();
        invalidPrevScreens.add(CurrentScreen.DEATH);
        invalidPrevScreens.add(CurrentScreen.VICTORY);
        invalidPrevScreens.add(CurrentScreen.CREDITS);

        justHovered = true;
    }

    @Override
    public boolean isClickable() {
        return (AbstractDungeon.player instanceof RyzaCharacter) && AbstractDungeon.isPlayerInDungeon() && (!invalidPrevScreens.contains(AbstractDungeon.previousScreen));
    }

    @Override
    protected void onClick() {
        if (isClickable()) {
            AbstractDungeon.player.releaseCard();
            if (AbstractDungeon.screen == RecipesScreen.Enums.RECIPES) {
                AbstractDungeon.closeCurrentScreen();
                return;
            }

            if (AbstractDungeon.isScreenUp) {
                AbstractDungeon.closeCurrentScreen();
            }

            BaseMod.openCustomScreen(RecipesScreen.Enums.RECIPES);
        }
    }

    @Override
    protected void onHover() {
        if (isClickable()) {
            if(this.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }
            TipHelper.renderGenericTip(TIP_X, TIP_Y, LABEL[0], MSG[0]);
            this.justHovered = false;
        }
    }

    @Override
    protected void onUnhover() {
        this.justHovered = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if (AbstractDungeon.player instanceof RyzaCharacter) {
            render(sb, isClickable() ? Color.WHITE : Color.LIGHT_GRAY);
        }
    }

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString(RyzaMod.makeID("RecipesTip"));
        MSG = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
        // uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("RecipesPanelItem"));
        // TEXT = uiStrings.TEXT;
        TIP_X = 1550.0F * Settings.xScale;
        TIP_Y = (float)Settings.HEIGHT - 120f * Settings.scale;
    }
}
