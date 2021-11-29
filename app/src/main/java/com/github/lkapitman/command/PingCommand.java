package com.github.lkapitman.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * The type Ping command.
 */
public class PingCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        final String[] args = event.getMessage().getContent().split("\\s");

        try {
            if (event.getMessage().getContent().contains(new StringBuffer(";ping")))
                event.getChannel().sendMessage("Pong! " + args[1]);
        } catch (Exception e) {
            event.getChannel().sendMessage("Pong!");
        }
    }

}
