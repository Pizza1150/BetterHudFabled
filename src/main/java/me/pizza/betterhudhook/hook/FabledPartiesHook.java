package me.pizza.betterhudhook.hook;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;
import kr.toxicity.hud.api.player.HudPlayer;
import studio.magemonkey.fabled.parties.FabledParties;
import studio.magemonkey.fabled.parties.Party;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FabledPartiesHook extends Hook {

    private final FabledParties fabledParties;

    public FabledPartiesHook() {
        super("FabledParties","fabled");
        fabledParties = (FabledParties) Bukkit.getPluginManager().getPlugin("FabledParties");
    }

    @Override
    public void hook() {
        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getBooleanContainer()
                .addPlaceholder(prefix + "_is_in_party", HudPlaceholder.of((args, reason) ->
                        (Function<HudPlayer, Boolean>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            Party party = fabledParties.getParty(player);
                            return party != null && party.isMember(player);
                        })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getStringContainer()
                .addPlaceholder(prefix + "_party_member_exclude_mine", HudPlaceholder.<String>builder()
                        .requiredArgsLength(1)
                        .function((args, reason) -> (Function<HudPlayer, String>) hudPlayer -> {
                            Player player = Bukkit.getPlayerExact(hudPlayer.name());
                            if (player == null) return "<none>";

                            int index = Integer.parseInt(args.getFirst());

                            Party party = fabledParties.getParty(player);
                            if (party == null || !party.isMember(player)) return "<none>";

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