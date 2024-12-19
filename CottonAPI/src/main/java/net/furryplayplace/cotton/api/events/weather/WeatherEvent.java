package net.furryplayplace.cotton.api.events.weather;

import net.furryplayplace.cotton.api.events.Event;
import net.minecraft.server.world.ServerWorld;
import org.jetbrains.annotations.NotNull;

public abstract class WeatherEvent extends Event {
    protected ServerWorld world;

    public WeatherEvent(@NotNull final ServerWorld where) {
        world = where;
    }

    @NotNull
    public final ServerWorld getWorld() {
        return world;
    }
}
