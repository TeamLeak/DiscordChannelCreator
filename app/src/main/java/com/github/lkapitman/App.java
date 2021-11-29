package com.github.lkapitman;

import com.github.lkapitman.command.PingCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

/**
 * The type App.
 */
public class App {

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        DiscordApi api = new DiscordApiBuilder().setToken("ODg4MDM4NjE2OTEwMTY0MDA5.YUM4aQ.-D4XI71ug3sLUnVROOxpzUj5C3Q").login().join();

        api.addListener(new PingCommand());
    }

}
