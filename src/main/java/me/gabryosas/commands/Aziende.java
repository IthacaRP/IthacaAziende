package me.gabryosas.commands;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.AziendeStorage;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.*;
import me.gabryosas.utils.objects.Cassa;
import me.gabryosas.utils.internal.Inventory;
import me.gabryosas.utils.internal.ItemStack;
import me.gabryosas.utils.vault.VaultUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Aziende implements CommandExecutor {
    /**
     Classe che gestisce il comando principale (/aziende).
     Bisogna aggiungere il paramentro reload.
     **/
    public static HashMap<Player, String> addContratto = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§cNon puoi eseguire questo comando dalla console!");
            return true;
        }
        Player player = (Player) sender;
        AziendeStorage aziendeStorage = new AziendeStorage(IthacaAziende.plugin);
        CasseStorage casseStorage = new CasseStorage(IthacaAziende.plugin);
        if (args.length == 0 || args.length >= 5){
            ConfigUtils.getHelpMessage(player);
            return true;
        }
        if (args.length == 1){
            if (args[0].equalsIgnoreCase("accetta")){
                if (!addContratto.containsKey(player)){
                    player.sendMessage(ConfigUtils.ERROR_ACCEPT);
                    return true;
                }
                LuckPermsAPI.addPlayerToGroup(player, addContratto.get(player));
                addContratto.remove(player);
            }
        }
        if (args.length == 2){
            if (args[0].equalsIgnoreCase("borsa")){
                if (!player.hasPermission("azienda." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                player.sendMessage(ConfigUtils.sendMoney(args[1]));
            }
            if (args[0].equalsIgnoreCase("imposta")){
                if (!player.hasPermission("azienda.admin")){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                Block blockMaterial = player.getTargetBlock(null, 10);
                if (blockMaterial.getType() != Cassa.getMaterial()){
                    player.sendMessage(ConfigUtils.ERROR_SETTINGS);
                    return true;
                }
                Location location = blockMaterial.getLocation();
                if (casseStorage.checkCassa(location)){
                    player.sendMessage(ConfigUtils.ERROR_SETTINGS_ID);
                    return true;
                }
                casseStorage.addCassa(location, args[1].toLowerCase());
                player.sendMessage(ConfigUtils.ADD_SETTINGS);
            }
            if (args[0].equalsIgnoreCase("crea")){
                if (!player.hasPermission("azienda.admin")){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_CREATE);
                    return true;
                }
                player.sendMessage(ConfigUtils.CREATE_AZIENDA);
                aziendeStorage.addAzienda(args[1].toLowerCase());
            }
            if (args[0].equalsIgnoreCase("give")){
                if (!player.hasPermission("azienda.admin")){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!args[1].equalsIgnoreCase("cassa")){
                    player.sendMessage(ConfigUtils.GIVE_FAIL);
                    return true;
                }
                player.sendMessage(ConfigUtils.GIVE_ITEM);
                if (args[1].equalsIgnoreCase("cassa")){
                    player.getInventory().addItem(ItemStack.createCostumItem(Cassa.getMaterial(), Cassa.getName(), Cassa.getModelData(), Cassa.getLore(), 1));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("rimuovi")){
                if (!player.hasPermission("azienda.admin")){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                aziendeStorage.removeAziende(args[1].toLowerCase());
                player.sendMessage(ConfigUtils.DELETE_AZIENDA);
            }
        }
        if (args.length == 3){
            if (args[0].equalsIgnoreCase("licenzia")){
                if (!player.hasPermission("azienda." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target == null){
                    player.sendMessage(ConfigUtils.OFFLINE_PLAYER);
                    return true;
                }
                if (target.isOp()){
                    player.sendMessage(ConfigUtils.ERROR_PLAYER);
                    return true;
                }
                if (!target.hasPermission("dipendente." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.ERROR_PLAYER);
                    return true;
                }
                LuckPermsAPI.removePlayerToGroup(target);
                player.sendMessage(ConfigUtils.DEPEX_SEND);
                target.sendMessage(ConfigUtils.DEPEX_TARGET);
            }
            if (args[0].equalsIgnoreCase("deposita")){
                if (!player.hasPermission("azienda." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                if (!IthacaAziende.isInt(args[2])){
                    player.sendMessage(ConfigUtils.ERROR_NUMBER);
                    return true;
                }
                if (!VaultUtils.checkMoney(player, Integer.parseInt(args[2]))){
                    player.sendMessage(ConfigUtils.FAIL_DEPOSITARE);
                    return true;
                }
                VaultUtils.removeMoney(player, Integer.parseInt(args[2]));
                aziendeStorage.addMoney(args[1].toLowerCase(), Integer.parseInt(args[2]));
                player.sendMessage(ConfigUtils.onDepositare(args[1], Integer.parseInt(args[2])));
            }
            if (args[0].equalsIgnoreCase("prelevare")){
                if (!player.hasPermission("azienda." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                if (!IthacaAziende.isInt(args[2])){
                    player.sendMessage(ConfigUtils.ERROR_NUMBER);
                    return true;
                }
                if (!VaultUtils.checkMoneyAziende(args[1].toLowerCase(), Integer.parseInt(args[2]))){
                    player.sendMessage(ConfigUtils.FAIL_PRELEVARE);
                    return true;
                }
                VaultUtils.addMoney(player, Integer.parseInt(args[2]));
                aziendeStorage.removeMoney(args[1].toLowerCase(), Integer.parseInt(args[2]));
                player.sendMessage(ConfigUtils.onPrelevare(args[1], Integer.parseInt(args[2])));
            }
        }
        if (args.length == 4){
            if (args[0].equalsIgnoreCase("assumi")){
                if (!player.hasPermission("azienda." + args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.NO_PERMISSION);
                    return true;
                }
                if (!aziendeStorage.checkAziende(args[1].toLowerCase())){
                    player.sendMessage(ConfigUtils.FAIL_DELETE);
                    return true;
                }
                Player target = Bukkit.getPlayerExact(args[2]);
                if (target == null){
                    player.sendMessage(ConfigUtils.OFFLINE_PLAYER);
                    return true;
                }
                if (player.getLocation().distanceSquared(target.getLocation()) > ConfigUtils.GET_DISTANCE){
                    player.sendMessage(ConfigUtils.ERROR_DISTANCE);
                    return true;
                }
                if (LuckPermsAPI.isPlayerInGroup(target, args[3].toLowerCase())){
                    player.sendMessage(ConfigUtils.ERROR_ADD_PLAYER);
                    return true;
                }
                if (target.getName().equalsIgnoreCase(player.getName())){
                    player.sendMessage(ConfigUtils.ERROR_PLAYER_SELECT);
                    return true;
                }
                addContratto.put(target, args[3]);
                player.sendMessage(ConfigUtils.REQUEST_SEND);
                ConfigUtils.sendContractMessage(target);
            }
        }
        return true;
    }
}
