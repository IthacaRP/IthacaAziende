package me.gabryosas.listeners;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.CasseStorage;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBreakCassa implements Listener {
    /**
     Classe che gestisce la rottura del blocco cassa tramite CasseStorage.
     **/
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        Location location = block.getLocation();
        CasseStorage casseStorage = new CasseStorage(IthacaAziende.plugin);
        if (!casseStorage.checkCassa(location)) return;
        casseStorage.removeCassa(casseStorage.getID(location));
    }
}
