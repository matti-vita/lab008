package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.ImplDeathNote;

import static org.junit.jupiter.api.Assertions.*;
import static it.unibo.deathnote.api.DeathNote.RULES;

class TestDeathNote {

    private DeathNote deathNote;

    @BeforeEach
    void setUp() {
        deathNote = new ImplDeathNote();
    } 

    @Test
    void testIllegalNumberRule() {
        for (final int index : List.of(0, -1, RULES.size() + 1)) {
            try {
                deathNote.getRule(index);
                fail("Expected an IllegalArgumentException to be thrown for index: " + index);
            } catch (final IllegalArgumentException e) {
                assertNotNull(e.getMessage());
                assertFalse(e.getMessage().isBlank());
            }
        }
    } 
}