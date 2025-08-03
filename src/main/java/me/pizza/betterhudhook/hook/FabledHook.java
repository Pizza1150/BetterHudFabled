package me.pizza.betterhudhook.hook;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;
import kr.toxicity.hud.api.player.HudPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.api.player.PlayerClass;
import studio.magemonkey.fabled.api.player.PlayerData;

import java.util.function.Function;

public class FabledHook extends Hook {

    public FabledHook() {
        super("fabled");
    }

    @Override
    public void hook() {
        BetterHudAPI.inst()
                .getListenerManager()
                .addListener(prefix + "_mana", yaml -> updateEvent -> (hudPlayer) -> {
                    Player player = Bukkit.getPlayerExact(hudPlayer.name());

                    PlayerData data = Fabled.getData(player);
                    if (data == null) return 0.0;

                    double mana = data.getMana();
                    double maxMana = data.getMaxMana();
                    return mana / maxMana;
                });

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder(prefix + "_mana", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            return data != null ? data.getMana() : 0.0;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder(prefix + "_max_mana", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            return data != null ? data.getMaxMana() : 0.0;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder(prefix + "_level", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            if (data == null) return 0;

                            PlayerClass mainClass = data.getMainClass();
                            return mainClass != null ? mainClass.getLevel() : 0;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder(prefix + "_current_exp", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            if (data == null) return 0.0;

                            PlayerClass mainClass = data.getMainClass();
                            return mainClass != null ? mainClass.getExp() : 0.0;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder(prefix + "_required_exp", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            if (data == null) return 0;

                            PlayerClass mainClass = data.getMainClass();
                            return mainClass != null ? mainClass.getRequiredExp() : 0;
                        })
                );
    }
}
