package net.furryplayplace.cotton.api.events.world;

import net.minecraft.server.world.ServerWorld;


public class WorldLoadEvent extends WorldEvent {
    public WorldLoadEvent(ServerWorld world) {
        super(world);
    }
}
