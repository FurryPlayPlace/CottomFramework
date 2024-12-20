/*
---------------------------------------------------------------------------------
File Name : PluginAlreadyRegisteredExceptions

Developer : vakea 
Email     : vakea@fluffici.eu
Real Name : Alex Guy Yann Le Roy

Date Created  : 19.12.2024
Last Modified : 19.12.2024

---------------------------------------------------------------------------------
*/

package net.furryplayplace.cottonframework.api.exceptions;

public class PluginAlreadyRegisteredExceptions extends Throwable {
    public PluginAlreadyRegisteredExceptions(String message) {
        super(message);
    }
    public PluginAlreadyRegisteredExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public PluginAlreadyRegisteredExceptions(Throwable cause) {
        super(cause);
    }
    public PluginAlreadyRegisteredExceptions() {
        super();
    }
}