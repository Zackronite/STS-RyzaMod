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
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import ryzamod.RyzaMod;
import ryzamod.character.RyzaCharacter;
import ryzamod.screens.MaterialBagScreen;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen;

public class MaterialBagTopPanelItem extends TopPanelItem {
    private static final Texture IMG = new Texture(RyzaMod.resourcePath("ui/bag.png"));
    public static final String ID = RyzaMod.makeID("MaterialBagButton");

    private static final TutorialStrings tutorialStrings;
    public static final String[] MSG;
    public static final String[] LABEL;
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private static final float BAG_TIP_X;
    private static final float BAG_TIP_Y;

    private final ArrayList<CurrentScreen> validScreens;
    private final ArrayList<CurrentScreen> invalidPrevScreens;
    private boolean justHovered;

    public MaterialBagTopPanelItem() {
        super(IMG, ID);

        validScreens = new ArrayList<>();
        validScreens.add(CurrentScreen.NONE);
        validScreens.add(CurrentScreen.HAND_SELECT);
        validScreens.add(CurrentScreen.MAP);
        validScreens.add(CurrentScreen.MASTER_DECK_VIEW);
        validScreens.add(CurrentScreen.GRID);
        validScreens.add(CurrentScreen.CHOOSE_ONE);
        validScreens.add(CurrentScreen.HAND_SELECT);
        validScreens.add(CurrentScreen.SETTINGS);
        validScreens.add(MaterialBagScreen.Enums.MATERIAL_BAG);

        invalidPrevScreens = new ArrayList<>();
        invalidPrevScreens.add(CurrentScreen.BOSS_REWARD);
        invalidPrevScreens.add(CurrentScreen.COMBAT_REWARD);
        invalidPrevScreens.add(CurrentScreen.DEATH);
        invalidPrevScreens.add(CurrentScreen.CREDITS);
        invalidPrevScreens.add(CurrentScreen.DOOR_UNLOCK);
        invalidPrevScreens.add(CurrentScreen.VICTORY);
        invalidPrevScreens.add(CurrentScreen.SHOP);

        justHovered = true;

        // maybe add keybind...?
    }

    @Override
    public boolean isClickable() {
        return (AbstractDungeon.player instanceof RyzaCharacter) && AbstractDungeon.getMonsters() != null && (validScreens.contains(AbstractDungeon.screen) && !(invalidPrevScreens.contains(AbstractDungeon.previousScreen)));
    }

    @Override
    protected void onClick() {
        if (isClickable()) {
            AbstractDungeon.player.releaseCard();
            if (AbstractDungeon.screen == MaterialBagScreen.Enums.MATERIAL_BAG) {
                AbstractDungeon.closeCurrentScreen();
                return;
            }

            if (AbstractDungeon.isScreenUp) {
                AbstractDungeon.closeCurrentScreen();
            }

            if (RyzaCharacter.materials.size() > 0) {
                BaseMod.openCustomScreen(MaterialBagScreen.Enums.MATERIAL_BAG);
            } else {
                AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3f, TEXT[0], true));
            }
        }
    }

    @Override
    protected void onHover() {
        if (isClickable()) {
            if (this.justHovered) {
                CardCrawlGame.sound.play("UI_HOVER");
            }
            TipHelper.renderGenericTip(BAG_TIP_X, BAG_TIP_Y, LABEL[0], MSG[0]);
            this.justHovered = false;
        }
    }

    @Override
    protected void onUnhover() {
        this.justHovered = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        // maybe add hover and/or flash indication?
        if (AbstractDungeon.player instanceof RyzaCharacter) {
            render(sb, isClickable() ? Color.WHITE : Color.LIGHT_GRAY);
        }
    }

    static {
        tutorialStrings = CardCrawlGame.languagePack.getTutorialString(RyzaMod.makeID("MaterialBagTip"));
        MSG = tutorialStrings.TEXT;
        LABEL = tutorialStrings.LABEL;
        uiStrings = CardCrawlGame.languagePack.getUIString(RyzaMod.makeID("MaterialBagPanelItem"));
        TEXT = uiStrings.TEXT;
        BAG_TIP_X = 1550.0F * Settings.xScale;
        BAG_TIP_Y = (float)Settings.HEIGHT - 120f * Settings.scale;
    }
}
