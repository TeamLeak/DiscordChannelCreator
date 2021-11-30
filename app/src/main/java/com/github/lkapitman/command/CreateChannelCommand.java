package com.github.lkapitman.command;

import com.vdurmont.emoji.EmojiParser;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CreateChannelCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        final String[] args = event.getMessage().getContent().split("\\s");

        if (event.getMessage().getContent().startsWith(";create")) {
            if (!(args.length > 2)) {
                event.getChannel().sendMessage("Правильное использование: \n ;create <name> <text/voice> ");
                return;
            }
        }

        String type = args[2];

        switch (type) {
            case "voice" -> {
                ServerVoiceChannel voice = new ServerVoiceChannelBuilder(event.getServer().get()).setName(args[1]).create().join();

                event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
            }
            case "text" -> {
                ServerTextChannel text = new ServerTextChannelBuilder(event.getServer().get()).setName(args[1]).create().join();

                event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
            }
            default -> {
                event.getChannel().sendMessage(
                        "Неправильный тип! \n" +
                        "text - текстовый \n" +
                        "voice - голосовой");
                event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsdown:"));
            }
        }
    }
}