package me.pizza.betterhudfabled.manager;

import me.pizza.betterhudfabled.hook.FabledHook;
import me.pizza.betterhudfabled.hook.FabledPartiesHook;
import me.pizza.betterhudfabled.hook.Hook;

import java.util.ArrayList;
import java.util.List;

public class HookManager {

    private final List<Hook> hooks = new ArrayList<>();

    public HookManager() {
        hooks.add(new FabledHook());
        hooks.add(new FabledPartiesHook());
    }

    public void load() {
        hooks.forEach(Hook::load);
    }
}
