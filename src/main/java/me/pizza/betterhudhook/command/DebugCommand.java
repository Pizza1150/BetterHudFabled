package me.pizza.betterhudhook.command;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

public class DebugCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        Set<String> listeners = BetterHudAPI.inst().getListenerManager().getAllListenerKeys();
        commandSender.sendMessage("§6Listeners:");
        for (String listener : listeners) {
            commandSender.sendMessage("§7- " + listener);
        }

        Map<String, HudPlaceholder<?>> numberContainers = BetterHudAPI.inst().getPlaceholderManager().getNumberContainer().getAllPlaceholders();
        commandSender.sendMessage("§6Number Placeholders:");
        for (Map.Entry<String, HudPlaceholder<?>> entry : numberContainers.entrySet()) {
            commandSender.sendMessage("§7- " + entry.getKey());
        }

        Map<String, HudPlaceholder<?>> booleanContainers = BetterHudAPI.inst().getPlaceholderManager().getBooleanContainer().getAllPlaceholders();
        commandSender.sendMessage("§6Boolean Placeholders:");
        for (Map.Entry<String, HudPlaceholder<?>> entry : booleanContainers.entrySet()) {
            commandSender.sendMessage("§7- " + entry.getKey());
        }

        Map<String, HudPlaceholder<?>> stringContainers = BetterHudAPI.inst().getPlaceholderManager().getStringContainer().getAllPlaceholders();
        commandSender.sendMessage("§6String Placeholders:");
        for (Map.Entry<String, HudPlaceholder<?>> entry : stringContainers.entrySet()) {
            commandSender.sendMessage("§7- " + entry.getKey());
        }

        return true;
    }
}
