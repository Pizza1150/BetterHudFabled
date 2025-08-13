package me.pizza.betterhudfabled.hook;

import kr.toxicity.hud.api.BetterHudAPI;
import kr.toxicity.hud.api.placeholder.HudPlaceholder;
import studio.magemonkey.fabled.parties.FabledParties;
import studio.magemonkey.fabled.parties.Party;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FabledPartiesHook implements Hook {

    private final FabledParties fabledParties;

    public FabledPartiesHook() {
        fabledParties = (FabledParties) Bukkit.getPluginManager().getPlugin("FabledParties");
    }

    @Override
    public void load() {
        // Placeholders
        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getBooleanContainer()
                .addPlaceholder("fabled_is_in_party", HudPlaceholder.of((args, reason) -> hudPlayer -> {
                        Player player = Bukkit.getPlayer(hudPlayer.uuid());
                        Party party = fabledParties.getParty(player);
                        return party != null && party.isMember(player);
                    })
                );

        BetterHudAPI.inst()
                .getPlaceholderManager()
                .getStringContainer()
                .addPlaceholder("fabled_party_member_exclude_mine", HudPlaceholder.<String>builder()
                        .requiredArgsLength(1)
                        .function((args, reason) -> hudPlayer -> {
                            Player player = Bukkit.getPlayer(hudPlayer.uuid());
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