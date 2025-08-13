package me.pizza.betterhudfabled.hook;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;
import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.api.player.PlayerClass;
import studio.magemonkey.fabled.api.player.PlayerData;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FabledHook implements Hook {
    
    @Override
    public void load() {
        // Listeners
        BetterHudAPI.inst()
                .getListenerManager()
                .addListener("fabled_mana", yaml -> updateEvent -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        if (data == null) return 0.0;
                        return data.getMana() / data.getMaxMana();
                });

        // Placeholders
        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_mana", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        return data != null ? data.getMana() : 0.0;
                    })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_max_mana", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        return data != null ? data.getMaxMana() : 0.0;
                    })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_level", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        if (data == null) return 0;

                        PlayerClass mainClass = data.getMainClass();
                        return mainClass != null ? mainClass.getLevel() : 0;
                    })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_current_exp", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        if (data == null) return 0.0;

                        PlayerClass mainClass = data.getMainClass();
                        return mainClass != null ? mainClass.getExp() : 0.0;
                    })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_required_exp", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        PlayerData data = Fabled.getData(player);
                        if (data == null) return 0;

                        PlayerClass mainClass = data.getMainClass();
                        return mainClass != null ? mainClass.getRequiredExp() : 0;
                    })
                );
    }
}
