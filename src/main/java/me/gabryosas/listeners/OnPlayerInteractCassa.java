package me.gabryosas.listeners;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.ConfigUtils;
import me.gabryosas.utils.internal.Inventory;
import me.gabryosas.utils.objects.Cassa;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class OnPlayerInteractCassa implements Listener {
    /**
     Questa classe gestisce quando il giocatore interagisce con la cassa.
     **/
    public static HashMap<Player, String> aziendaHashMap = new HashMap<>();
    public static HashMap<Player, Location> getIDCassa = new HashMap<>();
    @EventHandler
    public void onPlayerInteractCassa(PlayerInteractEvent e){
        Player player = e.getPlayer();
        CasseStorage casseStorage = new CasseStorage(IthacaAziende.plugin);
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        Block block = e.getClickedBlock();
        if (block.getType() != Cassa.getMaterial()) return;
        Location location = block.getLocation();
        if (!casseStorage.checkCassa(location)) return;
        if (!player.hasPermission("dipendente." + casseStorage.getAzienda(location).toLowerCase())){
            player.sendMessage(ConfigUtils.NO_PERMISSION);
            return;
        }
        getIDCassa.put(player, location);
        aziendaHashMap.put(player, casseStorage.getAzienda(location));
        player.openInventory(Inventory.createInventoryBorsa(casseStorage.getAzienda(location).toLowerCase()));
    }
}
