package com.github.lkapitman;

import com.github.lkapitman.command.CreateChannelCommand;
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
        DiscordApi api = new DiscordApiBuilder().setToken(args[0]).login().join();

        api.addListener(new PingCommand());
        api.addListener(new CreateChannelCommand());
    }

}
