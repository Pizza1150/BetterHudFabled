package me.pizza.betterhudhook;

import me.pizza.betterhudhook.placeholder.FabledPlaceholder;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterHudHook extends JavaPlugin {

    @Override
    public void onEnable() {
        new FabledPlaceholder();
    }
}