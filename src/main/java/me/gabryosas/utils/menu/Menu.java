package me.gabryosas.utils.menu;

import me.gabryosas.listeners.OnPlayerInteractCassa;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.ConfigUtils;
import me.gabryosas.utils.menu.objects.Precedente;
import me.gabryosas.utils.menu.objects.Scontrini;
import me.gabryosas.utils.menu.objects.Successiva;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.List;


public class Menu implements Listener {
    /**
      Per favore non toccate ItemsPerPage altrimenti il caricamento non funzionerà correttamente.
     **/

    private HashMap<Player, Integer> page;
    private CasseStorage casseStorage;
    private JavaPlugin plugin;

    public Menu(JavaPlugin plugin, CasseStorage casseStorage) {
        page = new HashMap<>();
        this.plugin = plugin;
        this.casseStorage = casseStorage;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void openMenu(Player player, String randomID) {
        List<String> transazioni = casseStorage.getTransazioni(randomID);
        if (transazioni.isEmpty()) {
            player.sendMessage(ConfigUtils.ERROR_LOAD_SCONTRINI);
            return;
        }
        int itemsPerPage = 18;
        page.put(player, 1);
        openMenuPage(player, randomID, 1, itemsPerPage);
    }

    public void openMenuPage(Player player, String randomID, int currentPage, int itemsPerPage) {
        List<String> transazioni = casseStorage.getTransazioniPaginate(randomID, currentPage, itemsPerPage);
        Inventory inventory = Bukkit.createInventory(null, 27, "Transazioni - Pagina " + currentPage);
        for (String transactionID : transazioni) {
            String autore = casseStorage.getTransactionAutore(randomID, transactionID);
            double prezzo = casseStorage.getTransactionPrezzo(randomID, transactionID);
            String oggetti = casseStorage.getTransactionOggetti(randomID, transactionID);
            String data = casseStorage.getTransactionData(randomID, transactionID);
            String id = casseStorage.getTransactionID(randomID, transactionID);
            ItemStack transactionItem = createItem(autore, prezzo, oggetti, data, id);
            inventory.addItem(transactionItem);
        }
        ItemStack previousButton = createButton(Precedente.getName(), Precedente.getMaterial(), Precedente.getModelData(), Precedente.getLore());
        ItemStack nextButton = createButton(Successiva.getName(), Successiva.getMaterial(), Successiva.getModelData(), Successiva.getLore());
        inventory.setItem(Precedente.getSlot(), previousButton);
        inventory.setItem(Successiva.getSlot(), nextButton);
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().startsWith("Transazioni - Pagina")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();
            if (clickedItem == null) return;
            if (clickedItem.getItemMeta().getDisplayName().equals(Precedente.getName())) {
                int currentPage = page.getOrDefault(player, 1);
                if (currentPage > 1) {
                    page.put(player, currentPage - 1);
                    openMenuPage(player, casseStorage.getID(OnPlayerInteractCassa.getIDCassa.get(player)), currentPage - 1, 18);
                }
            } else if (clickedItem.getItemMeta().getDisplayName().equals(Successiva.getName())) {
                int currentPage = page.getOrDefault(player, 1);
                int totalPages = (casseStorage.getTransazioni(casseStorage.getID(OnPlayerInteractCassa.getIDCassa.get(player))).size() - 1) / 18 + 1;
                if (currentPage < totalPages) {
                    page.put(player, currentPage + 1);
                    openMenuPage(player, casseStorage.getID(OnPlayerInteractCassa.getIDCassa.get(player)), currentPage + 1, 18);
                }
            }
        }
    }
    private ItemStack createButton(String name, Material material, int modeldata, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setCustomModelData(modeldata);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    private ItemStack createItem(String autore, double prezzo, String oggetti, String data, String ID) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(Scontrini.getModelData());
        meta.setDisplayName(Scontrini.getName(autore, oggetti, data, ID, (int) prezzo));
        meta.setLore(Scontrini.getLore(autore, oggetti, data, ID, (int) prezzo));
        item.setItemMeta(meta);
        return item;
    }
}