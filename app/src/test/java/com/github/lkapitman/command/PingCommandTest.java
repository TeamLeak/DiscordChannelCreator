package com.github.lkapitman.command;

import org.junit.jupiter.api.Test;

class PingCommandTest {
    PingCommand pingCommand = new PingCommand();

    @Test
    void testOnMessageCreate() {
        pingCommand.onMessageCreate(null);
    }
}

