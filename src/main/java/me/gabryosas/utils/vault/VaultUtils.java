package me.gabryosas.utils.vault;

import me.gabryosas.IthacaAziende;
import me.gabryosas.storage.AziendeStorage;
import org.bukkit.entity.Player;

public class VaultUtils {
    /**
     Classe che implementa metodi per usare Vault in modo semplice (Le API di Vault sono già semplici di loro).
     **/
    public static void removeMoney(Player player, int value){
        IthacaAziende.getEconomy().withdrawPlayer(player, value);
    }
    public static void addMoney(Player player, int value){
        IthacaAziende.getEconomy().depositPlayer(player, value);
    }
    public static boolean checkMoney(Player player, int value){
        if (IthacaAziende.getEconomy().getBalance(player) >= value){
            return true;
        }
        return false;
    }

    /**
    Non fa parte di Vault
     **/
    public static boolean checkMoneyAziende(String azienda, int value){
        AziendeStorage aziendeStorage = new AziendeStorage(IthacaAziende.plugin);
        if (aziendeStorage.getMoney(azienda) >= value){
            return true;
        }
        return false;
    }
}
