package mods.defeatedcrow.api.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

/**
 * 柚子ガトリングが弾を発射する直前に呼ばれるイベント。 <br>
 * 弾・チャージの減少処理の前のため、消費内容も変更可能。
 * キャンセル可。 <br>
 * 1.20.1: World → Level, EntityPlayer → Player。looseBullet/looseChargeは非final化し変更可能に。
 */
@Cancelable
@Event.HasResult
public class ShootingGunEvent extends Event {

    public final Level level;
    public final Player player;
    public final ItemStack gun;
    public float damage;
    public int looseBullet;
    public int looseCharge;

    public ShootingGunEvent(Level level, Player player, ItemStack item, float dam, int bullet, int charge) {
        this.level = level;
        this.player = player;
        this.gun = item;
        this.damage = dam;
        this.looseBullet = bullet;
        this.looseCharge = charge;
    }

}
