package com.github.lkapitman.command;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

/**
 * The type Ping command.
 */
public class PingCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        if (event.getMessage().getContent().startsWith(";ping")) {
            event.getChannel().sendMessage("Pong!");

        }
    }

}
