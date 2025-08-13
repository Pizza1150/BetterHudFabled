package me.pizza.betterhudfabled;

import me.pizza.betterhudfabled.manager.HookManager;

import org.bukkit.plugin.java.JavaPlugin;

public final class BetterHudFabled extends JavaPlugin {

    private final HookManager hookManager = new HookManager();

    @Override
    public void onEnable() {
        hookManager.load();
    }
}