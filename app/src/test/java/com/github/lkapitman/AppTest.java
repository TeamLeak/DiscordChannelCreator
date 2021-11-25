package com.github.lkapitman;

import com.github.lkapitman.command.Command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

class AppTest {
    @Mock
    Map<String, Command> commands;
    @InjectMocks
    App app;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testMain() {
        App.main(new String[]{"args"});
    }
}