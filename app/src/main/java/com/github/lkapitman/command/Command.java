package com.github.lkapitman.command;

import discord4j.core.event.domain.message.MessageCreateEvent;
import reactor.core.publisher.Mono;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute mono.
     *
     * Поскольку мы ожидаем выполнения в этом методе reactive вещей, например
     * отправляем сообщение, тогда этот метод также вернет reactive тип.
     *
     * @param event the event
     * @return the mono
     */
    Mono<Void> execute(MessageCreateEvent event);
}
