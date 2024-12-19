package net.furryplayplace.cotton.api.events.world;


import net.minecraft.entity.Entity;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EntitiesLoadEvent extends ChunkEvent {

    private final List<Entity> entities;

    public EntitiesLoadEvent(@NotNull WorldChunk chunk, @NotNull List<Entity> entities) {
        super(chunk);
        this.entities = entities;
    }

    @NotNull
    public List<Entity> getEntities() {
        return entities;
    }
}
