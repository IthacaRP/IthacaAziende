package me.gabryosas.storage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class AziendeStorage {
    /**
     Questa classe gestisce ogni metodo per la gestione del file aziende.yml
     **/

    private JavaPlugin plugin;
    private FileConfiguration aziendeConfig;
    private File aziendeFile;

    public AziendeStorage(JavaPlugin plugin) {
        this.plugin = plugin;
        this.aziendeFile = new File(plugin.getDataFolder(), "aziende.yml");
        if (!aziendeFile.exists()) {
            createFile();
        }
        this.aziendeConfig = YamlConfiguration.loadConfiguration(aziendeFile);
    }

    public void createFile() {
        File pluginFolder = plugin.getDataFolder();
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }
        File configFile = new File(pluginFolder, "aziende.yml");
        if (configFile.exists()) {
            return;
        }
        try {
            configFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveFile() {
        try {
            aziendeConfig.save(aziendeFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Errore durante il salvataggio del file aziende.yml!");
            e.printStackTrace();
        }
    }

    public void addAzienda(String nomeAzienda) {
        aziendeConfig.set(nomeAzienda + ".Soldi", 0);
        saveFile();
    }
    public void removeAziende(String nomeAzienda) {
        aziendeConfig.set(nomeAzienda, null);
        saveFile();
    }
    public int getMoney(String nomeAzienda){
        return aziendeConfig.getInt(nomeAzienda + ".Soldi");
    }
    public void addMoney(String nomeAzienda, int value){
        aziendeConfig.set(nomeAzienda + ".Soldi", getMoney(nomeAzienda) + value);
        saveFile();
    }
    public void removeMoney(String nomeAzienda, int value){
        aziendeConfig.set(nomeAzienda + ".Soldi", getMoney(nomeAzienda) - value);
        saveFile();
    }
    public boolean checkAziende(String nomeAzienda) {
        return aziendeConfig.contains(nomeAzienda);
    }
}