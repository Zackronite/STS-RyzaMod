package ryzamod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.FireBurstParticleEffect;

public class ElementFireAction extends AbstractGameAction {
    private DamageInfo info;

    public ElementFireAction(DamageInfo info, int amount) {
        this.info = info;
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = AttackEffect.FIRE;
        this.amount = amount;
    }

    public void update() {
        AbstractPlayer player = AbstractDungeon.player;
        AbstractCreature target = AbstractDungeon.getRandomMonster();

        if (target != null) {
            float speedTime = 0.15f;
            if (Settings.FAST_MODE) {
                speedTime = 0f;
            }

            for (int i = 0; i < this.amount; i++) {
                if (!AbstractDungeon.getMonsters().areMonstersDead()) {
                    addToTop(new DamageAction(target, this.info, AttackEffect.FIRE, true));
                    addToTop(new VFXAction(new FireBurstParticleEffect(target.drawX, target.drawY), speedTime));
                    addToTop(new SFXAction("ATTACK_FIRE"));
                    target = AbstractDungeon.getRandomMonster();
                }
            }
        }

        this.isDone = true;
    }
}
