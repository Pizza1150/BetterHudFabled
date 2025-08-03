package me.pizza.betterhudhook.placeholder;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;
import kr.toxicity.hud.api.player.HudPlayer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import studio.magemonkey.fabled.Fabled;
import studio.magemonkey.fabled.api.player.PlayerData;
import studio.magemonkey.fabled.parties.FabledParties;
import studio.magemonkey.fabled.parties.Party;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class FabledPlaceholder {

    private static final FabledParties FABLED_PARTIES = (FabledParties) Bukkit.getPluginManager().getPlugin("FabledParties");

    public FabledPlaceholder() {
        // Fabled
        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_mana", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            return data != null ? data.getMana() : 0.0;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getNumberContainer()
                .addPlaceholder("fabled_max_mana", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Number>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            PlayerData data = Fabled.getData(player);
                            return data != null ? data.getMaxMana() : 0.0;
                        })
                );

        // FabledParties
        if (FABLED_PARTIES == null) return;

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getBooleanContainer()
                .addPlaceholder("fabled_is_in_party", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Boolean>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            Party party = FABLED_PARTIES.getParty(player);
                            return party != null;
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getStringContainer()
                .addPlaceholder("fabled_party_member_exclude_mine", HudPlaceholder.<String>builder()
                        .requiredArgsLength(1)
                        .function((args, reason) -> (Function<HudPlayer, String>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            if (player == null) return "<none>";

                            int index = Integer.parseInt(args.getFirst());

                            Party party = FABLED_PARTIES.getParty(player);
                            if (party == null) return "<none>";

                            List<UUID> membersExcludeMine = party.getMembers().stream()
                                    .filter(uuid -> !uuid.equals(player.getUniqueId()))
                                    .toList();

                            if (index < 0 || index >= membersExcludeMine.size()) return "<none>";

                            Player target = Bukkit.getPlayer(membersExcludeMine.get(index));
                            return target != null ? target.getName() : "<none>";
                        })
                        .build()
                );
    }
}
