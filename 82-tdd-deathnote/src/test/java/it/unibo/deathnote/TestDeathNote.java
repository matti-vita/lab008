package it.unibo.deathnote;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.ImplDeathNote;

import static org.junit.jupiter.api.Assertions.*;
import static it.unibo.deathnote.api.DeathNote.RULES;
import static java.lang.Thread.sleep;

class TestDeathNote {

    private static final String MARIO = "Mario";
    private static final String LUIGI = "Luigi";
    private static final String KARTING_ACCIDENT = "Karting accident";
    private static final String RAN_LONG = "Ran for too long";
    private static final int INVALID_CAUSE_TIME = 100;

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

    @Test 
    void testEmptyNullRule() {
        for (int i = 1 ; i <= RULES.size() ; i++) {
            final String rule = deathNote.getRule(i);
            assertNotNull(rule);
            assertFalse(rule.isBlank());
        }
    }

    @Test 
    void testHumanDiesWhenWritten() {
        assertFalse(deathNote.isNameWritten(MARIO));
        deathNote.writeName(MARIO);
        assertTrue(deathNote.isNameWritten(MARIO));
        assertFalse(deathNote.isNameWritten(LUIGI));
        assertFalse(deathNote.isNameWritten(""));
    }

    @Test 
    void testCauseOfDeath() throws InterruptedException {
        try {
            deathNote.writeDeathCause("Too much Java");
            fail("Expected an IllegalStateException because the person has not yet been written");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(MARIO);
        assertEquals("Heart attack", deathNote.getDeathCause(MARIO));
        deathNote.writeName(LUIGI);
        assertTrue(deathNote.writeDeathCause(KARTING_ACCIDENT));
        assertEquals(KARTING_ACCIDENT, deathNote.getDeathCause(LUIGI));
        sleep(INVALID_CAUSE_TIME);
        assertFalse(deathNote.writeDeathCause("Nutella intoxication"));
        assertEquals(KARTING_ACCIDENT, deathNote.getDeathCause(LUIGI));
    }

    @Test 
    void testDetailsOfDeath() throws InterruptedException {
        try {
            deathNote.writeDetails("No more details");
            fail("Expected an IllegalStateException because the person has not yet been written");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathNote.writeName(MARIO);
        assertEquals("", deathNote.getDeathDetails(MARIO));
        assertTrue(deathNote.writeDetails(RAN_LONG));
        assertEquals(RAN_LONG, deathNote.getDeathDetails(MARIO));
        deathNote.writeName(LUIGI);
        sleep(6100);
        assertFalse(deathNote.writeDetails("Important details"));
        assertEquals("", deathNote.getDeathDetails(LUIGI));
    }
}