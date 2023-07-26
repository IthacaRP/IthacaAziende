package me.gabryosas.utils;

import me.gabryosas.IthacaAziende;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LuckPermsAPI {
    /**
     Classe che implementa 3 metodi per usare le API di LuckPerms.
     **/
    public static void addPlayerToGroup(Player player, String newGroupName) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        for (Node node : user.data().toCollection()) {
            if (node.getType() == NodeType.INHERITANCE && node.getKey().startsWith("group.")) {
                user.data().remove(node);
                break;
            }
        }
        Node newNode = Node.builder("group." + newGroupName).build();
        user.data().add(newNode);
        luckPerms.getUserManager().saveUser(user);
        Bukkit.getScheduler().runTaskAsynchronously(IthacaAziende.plugin, () -> {
            luckPerms.getUserManager().cleanupUser(user);
        });
        player.sendMessage(ConfigUtils.ADD_PLAYER);
    }
    public static void removePlayerToGroup(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        for (Node node : user.data().toCollection()) {
            if (node.getType() == NodeType.INHERITANCE && node.getKey().startsWith("group.")) {
                user.data().remove(node);
                break;
            }
        }
        Node newNode = Node.builder("group.default").build();
        user.data().add(newNode);
        luckPerms.getUserManager().saveUser(user);
        Bukkit.getScheduler().runTaskAsynchronously(IthacaAziende.plugin, () -> {
            luckPerms.getUserManager().cleanupUser(user);
        });
        player.sendMessage(ConfigUtils.ADD_PLAYER);
    }
    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }
}
