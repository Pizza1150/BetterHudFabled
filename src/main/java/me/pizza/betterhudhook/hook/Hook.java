package me.pizza.betterhudhook.hook;

public abstract class Hook {

    protected final String prefix;

    protected Hook(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public abstract void register();
}
