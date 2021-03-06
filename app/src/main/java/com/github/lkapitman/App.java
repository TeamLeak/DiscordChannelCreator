package com.github.lkapitman;

import com.github.lkapitman.command.CreateChannelCommand;
import com.github.lkapitman.command.PingCommand;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.channel.ChannelCategory;

/**
 * The type App.
 */
public class App {

    private static DiscordApi api;

    /**
     * Main.
     *
     * @param args the args
     */

    public static void main(final String[] args) {
        api = new DiscordApiBuilder().setToken(args[0]).login().join();

        api.addListener(new PingCommand());
        api.addListener(new CreateChannelCommand());
    }

    public static ChannelCategory channelCategory(String name) {
        for (ChannelCategory cc : api.getChannelCategories()) {
            if (cc.getName().equalsIgnoreCase(name)) return cc;
        }
        return null;
    }

}
