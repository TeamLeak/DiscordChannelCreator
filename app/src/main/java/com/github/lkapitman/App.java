package com.github.lkapitman;

import com.github.lkapitman.command.Command;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;

import java.util.HashMap;
import java.util.Map;

public class App {

    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("ping", event -> event.getMessage()
                .getChannel().block()
                .createMessage("Pong!").block());
    }

    public static void main(final String[] args) {
        final GatewayDiscordClient client = DiscordClientBuilder.create("ODg4MDM4NjE2OTEwMTY0MDA5.YUM4aQ.bHQdtK8bD4O-pleDVWT5KtpbPU4").build()
                .login()
                .block();

        client.getEventDispatcher().on(MessageCreateEvent.class)

                .subscribe(event -> {
                    final String content = event.getMessage().getContent();

                    for (final Map.Entry<String, Command> entry : commands.entrySet()) {
                        if (content.startsWith('!' + entry.getKey())) {
                            entry.getValue().execute(event);
                            break;
                        }
                    }
                });

        client.onDisconnect().block();
    }

}
