package me.gabryosas.utils.internal;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.objects.gui.Fondi;
import me.gabryosas.utils.objects.gui.Scontrino;
import me.gabryosas.utils.objects.gui.ScontrinoGUI;
import me.gabryosas.utils.objects.gui.Visualizza;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.SkullMeta;

public class Inventory {
    /**
    Questa classe gestisce la creazione delle varie GUI.
     **/
    public static org.bukkit.inventory.Inventory createInventoryBorsa(String name){
        org.bukkit.inventory.Inventory inventory = Bukkit.createInventory(null, IthacaAziende.plugin.getConfig().getInt("Items.cassa-gui.Max-Slot"), IthacaAziende.plugin.getConfig().getString("Items.cassa-gui.Name").replace("%azienda%", name));
        inventory.setItem(Visualizza.getSlot(), ItemStack.createCostumItem(Visualizza.getMaterial(), Visualizza.getName(), Visualizza.getModelData(), Visualizza.getLore(), 1));
        inventory.setItem(Scontrino.getSlot(), ItemStack.createCostumItem(Scontrino.getMaterial(), Scontrino.getName(), Scontrino.getModelData(), Scontrino.getLore(), 1));
        inventory.setItem(Fondi.getSlot(), ItemStack.createCostumItem(Fondi.getMaterial(), Fondi.getName(), Fondi.getModelData(), Fondi.getLore(), 1));
        return inventory;
    }
    public static org.bukkit.inventory.Inventory createSelectInventory(Player player, String name, int distance, int maxslot) {
        org.bukkit.inventory.Inventory inventory = Bukkit.createInventory(null, maxslot, name);
        org.bukkit.inventory.ItemStack requestedPlayerHead = getPlayerHead(player);
        inventory.addItem(requestedPlayerHead);
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[0]);
        for (Player onlinePlayer : onlinePlayers) {
            if (!onlinePlayer.equals(player)) {
                double distanceBetweenPlayers = onlinePlayer.getLocation().distance(player.getLocation());
                if (distanceBetweenPlayers <= distance && inventory.firstEmpty() != -1) {
                    org.bukkit.inventory.ItemStack otherPlayerHead = getPlayerHead(onlinePlayer);
                    inventory.addItem(otherPlayerHead);
                }
            }
        }

        return inventory;
    }

    private static org.bukkit.inventory.ItemStack getPlayerHead(Player player) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(player.getUniqueId());
        org.bukkit.inventory.ItemStack playerHead = new org.bukkit.inventory.ItemStack(Material.PLAYER_HEAD);
        if (offlinePlayer.getName() != null) {
            SkullMeta itemMeta = (SkullMeta) playerHead.getItemMeta();
            itemMeta.setOwningPlayer(offlinePlayer);
            itemMeta.setDisplayName(Color.translateHexColorCodes(ScontrinoGUI.getPrefixColor() + offlinePlayer.getName()));
            itemMeta.setLore(ScontrinoGUI.getLorePlayer());
            playerHead.setItemMeta(itemMeta);
        }
        return playerHead;
    }
}
