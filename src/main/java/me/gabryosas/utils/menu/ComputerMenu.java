package me.gabryosas.utils.menu;

import com.google.gson.JsonObject;
import me.gabryosas.listeners.OnPlayerInteractComputer;
import me.gabryosas.storage.ControllaStorage;
import me.gabryosas.utils.menu.computer.Controllo;
import me.gabryosas.utils.menu.computer.Precedente;
import me.gabryosas.utils.menu.computer.Successiva;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import java.util.List;

public class ComputerMenu implements Listener {
    //è la prima volta che lavoro con il JSON scusate se il codice può risultare così così...
    public static HashMap<Player, Integer> playerPageMap = new HashMap<>();

    public void openMenu(Player player, String azienda, int page) {
        Inventory inventory = Bukkit.createInventory(null, 27, Controllo.getName1());
        ControllaStorage controllaStorage = new ControllaStorage();
        List<JsonObject> aziendaItems = controllaStorage.getItemsByAzienda(azienda);
        int startIndex = (page - 1) * 18;
        int endIndex = Math.min(startIndex + 18, aziendaItems.size());
        int slot = 0;
        for (int i = startIndex; i < endIndex; i++) {
            JsonObject itemData = aziendaItems.get(i);
            String id = itemData.get("Id").getAsString();
            int importo = itemData.get("Importo").getAsInt();
            String autore = itemData.get("Autore").getAsString();
            String data = itemData.get("Data").getAsString();
            ItemStack item = me.gabryosas.utils.internal.ItemStack.createCostumItem(Controllo.getMaterial(), Controllo.getName(autore, OnPlayerInteractComputer.controlloHashMap.get(player), data, id, importo), Controllo.getModelData(), Controllo.getLore(autore, OnPlayerInteractComputer.controlloHashMap.get(player), data, id, importo));
            inventory.setItem(slot, item);
            slot++;
        }
        inventory.setItem(Successiva.getSlot(), me.gabryosas.utils.internal.ItemStack.createCostumItem(Successiva.getMaterial(), Successiva.getName(), Successiva.getModelData(), Successiva.getLore()));
        inventory.setItem(Precedente.getSlot(), me.gabryosas.utils.internal.ItemStack.createCostumItem(Precedente.getMaterial(), Precedente.getName(), Precedente.getModelData(), Precedente.getLore()));
        playerPageMap.put(player, page);
        player.openInventory(inventory);
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        String azienda = OnPlayerInteractComputer.controlloHashMap.get(player);
        ControllaStorage controllaStorage = new ControllaStorage();
        if (!e.getView().getTitle().equalsIgnoreCase(Controllo.getName1())) return;
        e.setCancelled(true);
        if (clickedItem == null) return;
        if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Successiva.getName())) {
            if ((playerPageMap.get(player) * 18) < controllaStorage.getNumItemsByAzienda(azienda)) {
                playerPageMap.put(player, playerPageMap.get(player) + 1);
                openMenu(player, azienda, playerPageMap.get(player));
                return;
            }
        }
        if (clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase(Precedente.getName())) {
            if (playerPageMap.get(player) > 1) {
                playerPageMap.put(player, playerPageMap.get(player) - 1);
                openMenu(player, azienda, playerPageMap.get(player));
            }
        }
    }
}