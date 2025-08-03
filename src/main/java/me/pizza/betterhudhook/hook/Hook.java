package me.pizza.betterhudhook.hook;

public abstract class Hook {

    protected final String name, prefix;

    protected Hook(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }

    public abstract void hook();
}
