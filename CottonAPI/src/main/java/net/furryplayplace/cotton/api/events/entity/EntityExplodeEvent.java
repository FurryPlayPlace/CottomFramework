package net.furryplayplace.cotton.api.events.entity;

import lombok.Getter;
import lombok.Setter;
import net.furryplayplace.cotton.api.Location;
import net.furryplayplace.cotton.api.events.Cancellable;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntityExplodeEvent extends EntityEvent implements Cancellable {
    private boolean cancel;
    private final Location location;
    private final List<Block> blocks;
    @Getter
    @Setter
    private float yield;

    public EntityExplodeEvent(@NotNull final Entity what, @NotNull final Location location, @NotNull final List<Block> blocks, final float yield) {
        super(what);
        this.location = location;
        this.blocks = blocks;
        this.yield = yield;
        this.cancel = false;
    }

    @Override
    public boolean isCancelled() {
        return cancel;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancel = cancel;
    }

    @NotNull
    public List<Block> blockList() {
        return blocks;
    }

    @NotNull
    public Location getLocation() {
        return location.clone();
    }
}
