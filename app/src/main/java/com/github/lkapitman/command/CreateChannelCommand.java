package com.github.lkapitman.command;

import com.github.lkapitman.App;
import com.vdurmont.emoji.EmojiParser;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;

public class CreateChannelCommand implements MessageCreateListener {

    private static String type;
    private static String category;
    private static String name;

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessage().getContent().equalsIgnoreCase(";create")) {
            final String[] args = event.getMessage().getContent().split("\\s");

            if (args[0].equalsIgnoreCase(";create")) {
                if (!(args.length > 2)) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("Ошибка!")
                            .setDescription("Неправильное использование!")
                            .setAuthor("Manager")
                            .addField("Использование", ";create <name> <text/voice> <category>")
                            .setColor(Color.RED)
                            .setFooter("Vladimir Putin Company. 2021");

                    event.getChannel().sendMessage(embed);
                    return;
                } else {
                    type = args[2];
                    category = args[3];
                    name = args[1];
                }
            }


            if (App.channelCategory(category) == null) {
                category = "БЕСЕДКА";
            }

            switch (type) {
                case "voice" -> {
                    ServerVoiceChannel voice = new ServerVoiceChannelBuilder(event.getServer().get()).setName(name).setCategory(App.channelCategory(category)).create().join();

                    event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
                }
                case "text" -> {
                    ServerTextChannel text = new ServerTextChannelBuilder(event.getServer().get()).setName(name).setCategory(App.channelCategory(category)).create().join();

                    event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
                }
                case "" -> {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setTitle("Ошибка!")
                            .setDescription("Неправильный тип или категория!")
                            .setAuthor("Manager")
                            .addField("Типы", "text - текстовый \n voice - голосовой")
                            .addField("Категории", "Такой категории не существует!")
                            .setColor(Color.RED)
                            .setFooter("Vladimir Putin Company. 2021");

                    event.getChannel().sendMessage(embed);
                    event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsdown:"));
                }
            }
        } else {
            return;
        }
    }
}