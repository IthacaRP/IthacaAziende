package me.gabryosas;

import me.gabryosas.commands.Aziende;
import me.gabryosas.commands.Stipendio;
import me.gabryosas.listeners.OnBreakCassa;
import me.gabryosas.listeners.OnPlayerInteractCassa;
import me.gabryosas.listeners.OnPlayerInteractComputer;
import me.gabryosas.listeners.inventory.OnInventoryClickCassa;
import me.gabryosas.listeners.inventory.OnInventoryClickFondi;
import me.gabryosas.listeners.inventory.OnInventoryClickPlayerSelect;
import me.gabryosas.storage.AziendeStorage;
import me.gabryosas.storage.CasseStorage;
import me.gabryosas.storage.ControllaStorage;
import me.gabryosas.utils.TabCompleter;
import me.gabryosas.utils.menu.ComputerMenu;
import me.gabryosas.utils.menu.Menu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class IthacaAziende extends JavaPlugin {
    private static Economy econ = null;
    public static IthacaAziende plugin;
    private AziendeStorage aziendeStorage;
    private CasseStorage casseStorage;
    private ControllaStorage controlloStorage;
    @Override
    public void onEnable() {
        plugin = this;
        if (!setupEconomy() ) {
            getServer().getConsoleSender().sendMessage("§cDisabilito il plugin, Vault non trovato.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        registerCommands();
        registerTabCompleter();
        registerListeners();
        aziendeStorage = new AziendeStorage(this);
        aziendeStorage.createFile();
        casseStorage = new CasseStorage(this);
        casseStorage.createFile();
        casseStorage.checkDate();
        controlloStorage = new ControllaStorage();
        controlloStorage.createJSON();
    }
    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new OnPlayerInteractCassa(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClickCassa(), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClickPlayerSelect(), this);
        getServer().getPluginManager().registerEvents(new Menu(this), this);
        getServer().getPluginManager().registerEvents(new OnInventoryClickFondi(), this);
        getServer().getPluginManager().registerEvents(new OnBreakCassa(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerInteractComputer(), this);
        getServer().getPluginManager().registerEvents(new ComputerMenu(), this);
    }
    public void registerCommands(){
        this.getCommand("aziende").setExecutor(new Aziende());
        this.getCommand("stipedio").setExecutor(new Stipendio());
    }
    public void registerTabCompleter(){
        this.getCommand("aziende").setTabCompleter(new TabCompleter());
    }
    @Override
    public void onDisable() {

    }
    public static boolean isInt(String value){
        try {
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    public static Economy getEconomy() {
        return econ;
    }
}
