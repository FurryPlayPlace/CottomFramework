package net.furryplayplace.cotton.api.events.player;

import lombok.Getter;
import lombok.Setter;
import net.furryplayplace.cotton.api.events.Cancellable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PlayerItemDamageEvent extends PlayerEvent implements Cancellable {
    private final ItemStack item;
    @Setter
    @Getter
    private int damage;
    @Getter
    private final int originalDamage;
    private boolean cancelled = false;

    @Deprecated // Paper - Add pre-reduction damage
    public PlayerItemDamageEvent(@NotNull PlayerEntity player, @NotNull ItemStack what, int damage) {
        this(player, what, damage, damage);
    }

    public PlayerItemDamageEvent(@NotNull PlayerEntity player, @NotNull ItemStack what, int damage, int originalDamage) {
        super(player);
        this.item = what;
        this.damage = damage;
        this.originalDamage = originalDamage;
    }

    @NotNull
    public ItemStack getItem() {
        return item;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
