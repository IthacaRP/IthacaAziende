package me.gabryosas.commands;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.AziendeStorage;
import me.gabryosas.utils.ConfigUtils;
import me.gabryosas.utils.vault.VaultUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Stipendio implements CommandExecutor {
    /**
     Classe che gestisce il comando /stipedio.
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§cNon puoi eseguire questo comando dalla console");
            return true;
        }
        Player player = (Player) sender;
        AziendeStorage aziendeStorage = new AziendeStorage(IthacaAziende.plugin);
        if (args.length == 0 || args.length >= 4){
            ConfigUtils.getHelpMessageStipendio(player);
            return true;
        }
        if (args.length == 3){
            if (!player.hasPermission("azienda." + args[0].toLowerCase())){
                player.sendMessage(ConfigUtils.NO_PERMISSION);
                return true;
            }
            if (!aziendeStorage.checkAziende(args[0].toLowerCase())){
                player.sendMessage(ConfigUtils.FAIL_DELETE);
                return true;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if (target == null){
                player.sendMessage(ConfigUtils.OFFLINE_PLAYER);
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
            VaultUtils.addMoney(target, Integer.parseInt(args[2]));
            player.sendMessage(ConfigUtils.onPaghetta(Integer.parseInt(args[2]), target));
            target.sendMessage(ConfigUtils.PAYMENT_TARGET);
        }
        return true;
    }
}
