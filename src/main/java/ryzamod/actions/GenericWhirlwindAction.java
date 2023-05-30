package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.WhirlwindEffect;

public class GenericWhirlwindAction extends AbstractGameAction {
    public int[] multiDamage;
    private DamageInfo.DamageType damageType;
    private AbstractPlayer p;
    private int timesToHit;

    public GenericWhirlwindAction(AbstractPlayer p, int[] multiDamage, DamageInfo.DamageType damageType, int timesToHit) {
        this.multiDamage = multiDamage;
        this.damageType = damageType;
        this.p = p;
        this.timesToHit = timesToHit;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        for (int i = 0; i < this.timesToHit; i++) {
            if (i == 0) {
                addToBot(new SFXAction("ATTACK_WHIRLWIND"));
                addToBot(new VFXAction(new WhirlwindEffect(), 0.0f));
            }

            addToBot(new SFXAction("ATTACK_HEAVY"));
            addToBot(new VFXAction(this.p, new CleaveEffect(), 0.0f));
            this.addToBot(new DamageAllEnemiesAction(this.p, this.multiDamage, this.damageType, AttackEffect.NONE));
        }

        this.isDone = true;
    }
}
