package net.furryplayplace.cottonframework;

import com.google.common.eventbus.Subscribe;
import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.furryplayplace.cottonframework.api.CottonAPI;
import net.furryplayplace.cottonframework.api.plugin.CottonPlugin;
import net.furryplayplace.cottonframework.api.events.cotton.CottonPluginInitialize;
import net.furryplayplace.cottonframework.api.events.cotton.CottonPluginShutdown;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;

public class CottonFramework implements ModInitializer  {
    private final Logger logger = LogManager.getLogger("CottonFramework");

    public static final String MOD_ID = "cottonframework";
    public static final String MOD_NAME = "CottonFramework";
    public static final String MOD_VERSION = "1.0.0";
    public static final String MOD_AUTHOR = "Vakea";

    @Getter
    private static CottonFramework instance;

    @Getter
    public final CottonAPI api = new BaseCottonAPI();

    @Override
    public void onInitialize() {
        instance = this;

        this.logger.info("   ******    *******   ********** **********   *******   ****     **");
        this.logger.info("  **////**  **/////** /////**/// /////**///   **/////** /**/**   /**");
        this.logger.info(" **    //  **     //**    /**        /**     **     //**/**//**  /**");
        this.logger.info("/**       /**      /**    /**        /**    /**      /**/** //** /**");
        this.logger.info("/**       /**      /**    /**        /**    /**      /**/**  //**/**");
        this.logger.info("//**    **//**     **     /**        /**    //**     ** /**   //****");
        this.logger.info(" //******  //*******      /**        /**     //*******  /**    //***");
        this.logger.info("  //////    ///////       //         //       ///////   //      ///");
        this.logger.info("                 FurryPlayPlace - CottonFramework                  ");

        this.logger.info("Framework Information: ");
        this.logger.info(" - ID: {}", MOD_ID);
        this.logger.info(" - Name: {}", MOD_NAME);
        this.logger.info(" - Version: {}", MOD_VERSION);
        this.logger.info(" - Author: {}", MOD_AUTHOR);

        this.logger.info("No support will be provided for this framework.");
        this.logger.info("If you want to contribute, please visit https://github.com/FurryPlayPlace/CottonFramework");
        this.logger.info("If you want to report a bug, please visit https://github.com/FurryPlayPlace/CottonFramework/issues");
        this.logger.info("If you want to request a new feature, please visit https://github.com/FurryPlayPlace/CottonFramework/issues");

        this.logger.info("Awaiting for Cotton INIT Trigger...");

        this.api.pluginManager().getEventBus().register(this);

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            if (environment.dedicated) {
                dispatcher.register(literal("plugins").executes(commandContext -> {
                    List<String> pluginNames = this.api.pluginManager().getPluginsWithContainer().stream()
                            .map(cottonPlugin -> switch (cottonPlugin.getState()) {
                                case FAILURE, DISABLED, UNKNOWN -> Formatting.RED + cottonPlugin.getPlugin().name();
                                case ENABLED -> Formatting.GREEN + cottonPlugin.getPlugin().name();
                                case LOADED -> Formatting.GRAY + cottonPlugin.getPlugin().name();
                            }).toList();

                    commandContext.getSource().sendFeedback(() -> Text.literal("Loaded plugins: " + String.join(", ", pluginNames)), false);
                    return 0;
                }));

                dispatcher.register(literal("about-cotton").executes(commandContext -> {
                    commandContext.getSource().sendFeedback(() -> Text.literal("CottonFramework v" + MOD_VERSION), false);
                    commandContext.getSource().sendFeedback(() -> Text.literal("Developed by " + MOD_AUTHOR), false);
                    commandContext.getSource().sendFeedback(() -> Text.literal("Visit https://github.com/FurryPlayPlace/CottonFramework for more information"), false);
                    return 0;
                }));
            }
        });
    }

    @Subscribe
    public void onCottonInitialize(CottonPluginInitialize event) {
        this.logger.info("Initializing all plugins...");

        this.api.pluginManager().loadPlugins();

        // Start - Registering all commands logic

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> this.api.pluginManager().getCommands().forEach(command -> {
            if (environment.dedicated) {
                dispatcher.register(literal(command.getName()).executes(commandContext -> command.execute(commandContext, commandContext.getSource().getPlayer())));
                this.logger.info("Registered command: {}", command.getName());
            }
        }));

        // End - Registering all commands logic

        CottonAPI.get().pluginManager().enableAllPlugins();

        this.logger.info("All plugins have been initialized.");
        this.logger.info("CottonFramework is ready to use.");

        List<String> pluginNames = this.api.pluginManager().getPlugins().stream()
                .map(CottonPlugin::name).toList();

        this.logger.info("Loaded plugins: {}", String.join(", ", pluginNames));
    }

    @Subscribe
    public void onCottonShutdown(CottonPluginShutdown event) {
        this.logger.info("Shutting down all plugins...");

        this.api.pluginManager().disableAllPlugins();
        this.api.pluginManager().unloadPlugins();

        this.logger.info("All plugins have been unloaded.");
    }
}