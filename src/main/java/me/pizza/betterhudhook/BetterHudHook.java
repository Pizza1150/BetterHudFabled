package me.pizza.betterhudhook;

import me.pizza.betterhudhook.hook.FabledHook;
import me.pizza.betterhudhook.hook.FabledPartiesHook;
import me.pizza.betterhudhook.hook.Hook;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterHudHook extends JavaPlugin {

    private final List<Hook> hooks = new ArrayList<>();

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("Fabled") != null) registerHook(new FabledHook());
        if (Bukkit.getPluginManager().getPlugin("FabledParties") != null) registerHook(new FabledPartiesHook());

        hooks.forEach(Hook::register);
    }

    private void registerHook(Hook hook) {
        hooks.add(hook);
    }
}