package me.gabryosas.utils;

import me.gabryosas.IthacaAziende;
import me.gabryosas.commands.Aziende;
import me.gabryosas.storage.AziendeStorage;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.utils.internal.Color;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.List;

public class ConfigUtils {
    /**
     * Questa classe gestisce tutti i messaggi del file di configurazione.
     **/
    public static int GET_DISTANCE = IthacaAziende.plugin.getConfig().getInt("Settings.Distance");
    public static String PREFIX = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Settings.Prefix"));
    public static String NO_PERMISSION = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.No-Permission").replace("%prefix%", PREFIX));
    public static String FAIL_PRELEVARE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Fail.Fail-Prelevare").replace("%prefix%", PREFIX));
    public static String FAIL_DEPOSITARE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Fail.Fail-Depositare").replace("%prefix%", PREFIX));
    public static String OFFLINE_PLAYER = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Offline-Player").replace("%prefix%", PREFIX));
    public static String ERROR_PLAYER = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Player").replace("%prefix%", PREFIX));
    public static String FAIL_CREATE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Fail.Fail-Create").replace("%prefix%", PREFIX));
    public static String CREATE_AZIENDA = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Create-Azienda").replace("%prefix%", PREFIX));
    public static String FAIL_DELETE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Fail.Fail-Delete").replace("%prefix%", PREFIX));
    public static String GIVE_FAIL = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Fail.Give-Fail").replace("%prefix%", PREFIX));
    public static String GIVE_ITEM = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Give-Item").replace("%prefix%", PREFIX));
    public static String DELETE_AZIENDA = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Delete-Azienda").replace("%prefix%", PREFIX));
    public static String ADD_PLAYER = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Add-Player").replace("%prefix%", PREFIX));
    public static String ERROR_ADD_PLAYER = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Add-Player").replace("%prefix%", PREFIX));
    public static String ERROR_PLAYER_SELECT = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Player-Select").replace("%prefix%", PREFIX));
    public static String REQUEST_SEND = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Request-Send").replace("%prefix%", PREFIX));
    public static String ERROR_DISTANCE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Distance").replace("%prefix%", PREFIX));
    public static String ERROR_ACCEPT = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Accept").replace("%prefix%", PREFIX));
    public static String DEPEX_SEND = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Depex-Send").replace("%prefix%", PREFIX));
    public static String DEPEX_TARGET = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Depex-Target").replace("%prefix%", PREFIX));
    public static String ERROR_SETTINGS = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Settings").replace("%prefix%", PREFIX));
    public static String ADD_SETTINGS = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Settings-Cassa").replace("%prefix%", PREFIX));
    public static String ERROR_SETTINGS_ID = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Settings-ID").replace("%prefix%", PREFIX));
    public static String ERROR_NUMBER = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Number").replace("%prefix%", PREFIX));
    public static String ERROR_LOAD_SCONTRINI = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Error.Error-Scontrini-Load").replace("%prefix%", PREFIX));
    public static String CREATE_SCONTRINO = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Create-Scontrino").replace("%prefix%", PREFIX));
    public static String PAYMENT_TARGET = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Payment-Target").replace("%prefix%", PREFIX));
    public static String RELOAD_MESSAGE = me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Reload-Message").replace("%prefix%", PREFIX));
    public static String onDepositare(String azienda, int amount) {
        return me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Actions.On-Depositare").replace("%prefix%", PREFIX).replace("%soldi%", String.valueOf(amount)).replace("%azienda%", azienda.toLowerCase()));
    }

    public static String onPrelevare(String azienda, int amount) {
        return me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Actions.On-Prelevare").replace("%prefix%", PREFIX).replace("%soldi%", String.valueOf(amount)).replace("%azienda%", azienda.toLowerCase()));
    }

    public static String onPaghetta(int amount, Player target) {
        return me.gabryosas.utils.internal.Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Payment-Player").replace("%prefix%", PREFIX).replace("%soldi%", String.valueOf(amount)).replace("%player%", target.getName()));
    }
    public static String sendMoney(String azienda){
        AziendeStorage aziendeStorage = new AziendeStorage(IthacaAziende.plugin);
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Messages.Azienda.Send-Money").replace("%soldi%", String.valueOf(aziendeStorage.getMoney(azienda.toLowerCase()))).replace("%prefix%", PREFIX).replace("%azienda%", azienda.toLowerCase()));
    }

    public static void getHelpMessage(Player player) {
        FileConfiguration config = IthacaAziende.plugin.getConfig();
        if (config.contains("Messages.List-Commands")) {
            List<String> helpMessageList = config.getStringList("Messages.List-Commands");
            StringBuilder helpMessageBuilder = new StringBuilder();
            for (String line : helpMessageList) {
                String translatedLine = Color.translateHexColorCodes(line);
                helpMessageBuilder.append(translatedLine).append("\n");
            }
            String helpMessage = helpMessageBuilder.toString().trim();
            player.sendMessage(helpMessage);
        }
    }
    public static void getHelpMessageStipendio(Player player) {
        FileConfiguration config = IthacaAziende.plugin.getConfig();
        if (config.contains("Messages.Stipendio-Commands")) {
            List<String> helpMessageList = config.getStringList("Messages.Stipendio-Commands");
            StringBuilder helpMessageBuilder = new StringBuilder();
            for (String line : helpMessageList) {
                String translatedLine = Color.translateHexColorCodes(line);
                helpMessageBuilder.append(translatedLine).append("\n");
            }
            String helpMessage = helpMessageBuilder.toString().trim();
            player.sendMessage(helpMessage);
        }
    }
    public static void sendContractMessage(Player player) {
        int time = IthacaAziende.plugin.getConfig().getInt("Messages.Contratto.Time");
        for (String message : IthacaAziende.plugin.getConfig().getStringList("Messages.Contratto.Message")) {
            message = message.replace("%time%", String.valueOf(time));
            message = ChatColor.translateAlternateColorCodes('&', message);
            TextComponent gmcCommand = new TextComponent();
            gmcCommand.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/aziende accetta"));
            gmcCommand.setText(message);
            player.spigot().sendMessage(gmcCommand);
        }
        IthacaAziende.plugin.getServer().getScheduler().scheduleSyncDelayedTask(IthacaAziende.plugin, new Runnable() {
            @Override
            public void run() {
                Aziende.addContratto.remove(player);
                player.sendMessage(ConfigUtils.ERROR_ACCEPT);
            }
        }, time * 20);
    }
}

