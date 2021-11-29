package com.github.lkapitman.command;

import com.vdurmont.emoji.EmojiParser;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.ServerTextChannelBuilder;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.channel.ServerVoiceChannelBuilder;
import org.javacord.api.entity.server.invite.Invite;
import org.javacord.api.entity.server.invite.InviteBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class CreateChannelCommand implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        final String[] args = event.getMessage().getContent().split("\\s");

        if (!(args.length > 3)) {
            event.getChannel().sendMessage("Правильное использование: \n ;create <name> <people-count> <type> ");
            return;
        }
        String type = args[3];
        String maxCount = args[2];

        if (event.getMessage().getContent().startsWith(";create")) {
            Invite invite;
            
            switch (type) {
                case "voice" -> {
                    ServerVoiceChannel voice = new ServerVoiceChannelBuilder(event.getServer().get()).setName(args[1]).create().join();

                    event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
                    invite = new InviteBuilder(voice).setMaxAgeInSeconds(60*60*24)
                            .setMaxUses(Integer.parseInt(maxCount))
                            .create().join();

                    event.getChannel().sendMessage(invite.getUrl().toString());
                }
                case "text" -> {
                    ServerTextChannel text = new ServerTextChannelBuilder(event.getServer().get()).setName(args[1]).create().join();

                    event.getMessage().addReaction(EmojiParser.parseToUnicode(":thumbsup:"));
                    invite = new InviteBuilder(text).setMaxAgeInSeconds(60*60*24)
                            .setMaxUses(Integer.parseInt(maxCount))
                            .create().join();

                    event.getChannel().sendMessage(invite.getUrl().toString());
                }
            }
        }
    }


}
