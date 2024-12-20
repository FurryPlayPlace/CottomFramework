/*
---------------------------------------------------------------------------------
File Name : CottonAPI

Developer : vakea 
Email     : vakea@fluffici.eu
Real Name : Alex Guy Yann Le Roy

Date Created  : 19.12.2024
Last Modified : 19.12.2024

---------------------------------------------------------------------------------
*/

package net.furryplayplace.cottonframework;

import net.furryplayplace.cottonframework.api.CottonAPI;
import net.furryplayplace.cottonframework.api.CottonServer;
import net.furryplayplace.cottonframework.api.plugin.interfaces.ICommandManager;
import net.furryplayplace.cottonframework.manager.PluginManager;

public class BaseCottonAPI extends CottonAPI {
    private final PluginManager pluginManager = new PluginManager();
    private final CottonServer server = new CottonServer();

    public PluginManager pluginManager() {
        return this.pluginManager;
    }

    @Override
    public CottonServer server() {
        return this.server;
    }
}