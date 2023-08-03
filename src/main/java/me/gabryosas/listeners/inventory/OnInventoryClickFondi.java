package me.gabryosas.listeners.inventory;

import me.gabryosas.listeners.OnPlayerInteractCassa;
import me.gabryosas.utils.objects.gui.FondiGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;

public class OnInventoryClickFondi implements Listener {
    @EventHandler
    public void onClickInventory(InventoryClickEvent e){
        if (!e.getView().getTitle().startsWith(FondiGUI.getName())) return;
        e.setCancelled(true);
    }
}
