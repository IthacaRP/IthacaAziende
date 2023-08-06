package me.gabryosas.listeners;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.AziendeStorage;
import me.gabryosas.storage.ControllaStorage;
import me.gabryosas.utils.ConfigUtils;
import me.gabryosas.utils.menu.ComputerMenu;
import me.gabryosas.utils.menu.computer.Controllo;
import me.gabryosas.utils.objects.computer.Computer;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class OnPlayerInteractComputer implements Listener {
    /**
     Classe che può tornare utile per gestione del PlayerInteract (Quando il giocatore interagisce con il computer Finanza).
     **/
    public static HashMap<Player, String> controlloHashMap = new HashMap<>();
    @EventHandler
    public void onPlayerInteractCassa(PlayerInteractEvent e){
        Player player = e.getPlayer();
        AziendeStorage aziendeStorage = new AziendeStorage(IthacaAziende.plugin);
        if (e.getHand() != EquipmentSlot.HAND) return;
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (e.getClickedBlock().getType() != Computer.getMaterial()) return;
        if (!player.hasPermission("computer.finanza")){
            player.sendMessage(ConfigUtils.NO_PERMISSION);
            return;
        }
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        new AnvilGUI.Builder().onComplete((player2, oggetti) -> {
            itemMeta.setDisplayName(Controllo.getName2());
            itemStack.setItemMeta(itemMeta);
            if (!aziendeStorage.checkAziende(oggetti.toLowerCase())){
                player.sendMessage(ConfigUtils.FAIL_DELETE);
                return AnvilGUI.Response.close();
            }
            controlloHashMap.put(player, oggetti.toLowerCase());
            ControllaStorage controllaStorage = new ControllaStorage();
            ComputerMenu computerMenu = new ComputerMenu();
            computerMenu.openMenu(player, controlloHashMap.get(player), 1);
            return AnvilGUI.Response.close();
        }).plugin(IthacaAziende.plugin).text(Controllo.getName2()).title(Controllo.getName2()).itemLeft(itemStack).open(player);
    }
}
