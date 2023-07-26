package me.gabryosas.listeners.inventory;

import me.gabryosas.IthacaAziende;
import me.gabryosas.listeners.OnPlayerInteractCassa;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.internal.Inventory;
import me.gabryosas.utils.menu.Menu;
import me.gabryosas.utils.objects.gui.Fondi;
import me.gabryosas.utils.objects.gui.Scontrino;
import me.gabryosas.utils.objects.gui.ScontrinoGUI;
import me.gabryosas.utils.objects.gui.Visualizza;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class OnInventoryClickCassa implements Listener {
    /**
     Classe che gestisce l'evento del Click (GUI della cassa).
     Bisogna aggiungere la funzione per i fondi (Purtroppo non ho capito il funzionamento).
     **/
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        CasseStorage casseStorage = new CasseStorage(IthacaAziende.plugin);
        Player player = (Player) e.getWhoClicked();
        if (!e.getView().getTitle().startsWith(getNameGUI())) return;
        if (e.getCurrentItem() == null) return;
        e.setCancelled(true);
        if (e.getSlot() == Fondi.getSlot()){
            return;
        }
        if (e.getSlot() == Scontrino.getSlot()){
            player.openInventory(Inventory.createSelectInventory(player, ScontrinoGUI.getName1(), ScontrinoGUI.getDistance(), ScontrinoGUI.getMaxSlot()));
            return;
        }
        if (e.getSlot() == Visualizza.getSlot()){
            Menu menu = new Menu(IthacaAziende.plugin, casseStorage);
            menu.openMenu(player, casseStorage.getID(OnPlayerInteractCassa.getIDCassa.get(player)));
        }
    }
    public String getNameGUI() {
        FileConfiguration config = IthacaAziende.plugin.getConfig();
        String guiName = config.getString("Items.cassa-gui.Name");
        String[] words = guiName.split(" ");
        if (words.length > 0) {
            return words[0];
        } else {
            return guiName;
        }
    }
}
