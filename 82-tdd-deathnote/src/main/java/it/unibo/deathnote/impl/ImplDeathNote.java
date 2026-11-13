package it.unibo.deathnote.impl;

import it.unibo.deathnote.api.DeathNote;

public class ImplDeathNote implements DeathNote {

    @Override
    public String getRule(int ruleNumber) {
        throw new IllegalArgumentException();
    }

    @Override
    public void writeName(String name) {
        throw new NullPointerException();
    }

    @Override
    public boolean writeDeathCause(String cause) {
        throw new IllegalStateException();
    }

    @Override
    public boolean writeDetails(String details) {
        throw new IllegalStateException();
    }

    @Override
    public String getDeathCause(String name) {
        throw new IllegalArgumentException();
    }

    @Override
    public String getDeathDetails(String name) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isNameWritten(String name) {
        return false;
    }
}
