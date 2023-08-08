package me.gabryosas.listeners.inventory;

import me.gabryosas.IthacaAziende;
import me.gabryosas.listeners.OnPlayerInteractCassa;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.ConfigUtils;
import me.gabryosas.utils.internal.Date;
import me.gabryosas.utils.objects.gui.ScontrinoGUI;
import me.gabryosas.utils.vault.VaultUtils;
import net.md_5.bungee.api.ChatColor;
import net.wesjd.anvilgui.AnvilGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnInventoryClickPlayerSelect implements Listener {
    /**
     Questa classe gestisce una delle funzioni riguardante la cassa (scontrino).
     */
    @EventHandler
    public void onInventoryClickPlayerSelect(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if (!e.getView().getTitle().equalsIgnoreCase(ScontrinoGUI.getName1())) return;
        if (e.getCurrentItem() == null) return;
        e.setCancelled(true);
        Player target = Bukkit.getPlayerExact(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()));
        if (target == null){
            player.sendMessage(ConfigUtils.OFFLINE_PLAYER);
            return;
        }
        openGUI(player, target);
    }
    public void openGUI(Player player, OfflinePlayer target){
        CasseStorage casseStorage = new CasseStorage(IthacaAziende.plugin);
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta itemMeta = itemStack.getItemMeta();
        new AnvilGUI.Builder().onComplete((player2, oggetti) -> {
            itemMeta.setDisplayName(ScontrinoGUI.getName2());
            itemStack.setItemMeta(itemMeta);
            new AnvilGUI.Builder().onComplete((player3, prezzo) -> {
                itemMeta.setDisplayName(ScontrinoGUI.getName3());
                itemStack.setItemMeta(itemMeta);
                if (!IthacaAziende.isInt(prezzo)){
                    player.sendMessage(ConfigUtils.ERROR_NUMBER);
                    return AnvilGUI.Response.close();
                }
                if (!VaultUtils.checkMoney(player, Integer.parseInt(prezzo))){
                    player.sendMessage(ConfigUtils.FAIL_DEPOSITARE);
                    return AnvilGUI.Response.close();
                }
                VaultUtils.removeMoney(player, Integer.parseInt(prezzo));
                player.getInventory().addItem(me.gabryosas.utils.internal.ItemStack.createCostumItem(ScontrinoGUI.getMaterialScontrino(), ScontrinoGUI.getName(OnPlayerInteractCassa.aziendaHashMap.get(player)),ScontrinoGUI.getModelData(), ScontrinoGUI.getLore(Date.getDate(), target.getName(), player.getName(), Integer.parseInt(prezzo), oggetti), 2));
                casseStorage.addTransazione(casseStorage.getID(OnPlayerInteractCassa.getIDCassa.get(player)), player.getName(), Double.parseDouble(prezzo), oggetti, Date.getDate());
                player.sendMessage(ConfigUtils.CREATE_SCONTRINO);
                return AnvilGUI.Response.close();
            }).plugin(IthacaAziende.plugin).text(ScontrinoGUI.getName3()).title(ScontrinoGUI.getName3()).itemLeft(itemStack).open(player);
            return AnvilGUI.Response.close();
        }).plugin(IthacaAziende.plugin).text(ScontrinoGUI.getName2()).title(ScontrinoGUI.getName2()).itemLeft(itemStack).open(player);
    }
}
