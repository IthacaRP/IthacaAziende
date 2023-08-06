package me.gabryosas.storage;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class CasseStorage {
    /**
     Per la creazione di determinati metodi Es. Casse Menu ho usufruito di GabryLibs (Mie API).
     Classe che gestisce le varie casse.
     **/
    private JavaPlugin plugin;
    private FileConfiguration casseConfig;
    private File casseFile;

    public CasseStorage(JavaPlugin plugin) {
        this.plugin = plugin;
        this.casseFile = new File(plugin.getDataFolder(), "casse.yml");
        if (!casseFile.exists()) {
            createFile();
        }
        this.casseConfig = YamlConfiguration.loadConfiguration(casseFile);
    }

    public void createFile() {
        File pluginFolder = plugin.getDataFolder();
        if (!pluginFolder.exists()) {
            pluginFolder.mkdirs();
        }
        File configFile = new File(pluginFolder, "casse.yml");
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
            casseConfig.save(casseFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addCassa(Location location, String azienda) {
        String randomID = generateID();
        casseConfig.set(randomID + ".x", location.getX());
        casseConfig.set(randomID + ".y", location.getY());
        casseConfig.set(randomID + ".z", location.getZ());
        casseConfig.set(randomID + ".azienda", azienda);
        casseConfig.set(randomID + ".transazioni", new ArrayList<String>());
        saveFile();
    }
    public void removeCassa(String id){
        casseConfig.set(id, null);
        saveFile();
    }
    public String getAzienda(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        for (String randomID : casseConfig.getKeys(false)) {
            double xStored = casseConfig.getDouble(randomID + ".x");
            double yStored = casseConfig.getDouble(randomID + ".y");
            double zStored = casseConfig.getDouble(randomID + ".z");
            if (x == xStored && y == yStored && z == zStored) {
                return casseConfig.getString(randomID + ".azienda");
            }
        }
        return null;
    }
    public boolean checkCassa(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        for (String randomID : casseConfig.getKeys(false)) {
            double xStored = casseConfig.getDouble(randomID + ".x");
            double yStored = casseConfig.getDouble(randomID + ".y");
            double zStored = casseConfig.getDouble(randomID + ".z");
            if (x == xStored && y == yStored && z == zStored) {
                return true;
            }
        }
        return false;
    }

    public String getID(Location location) {
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        for (String randomID : casseConfig.getKeys(false)) {
            double xStored = casseConfig.getDouble(randomID + ".x");
            double yStored = casseConfig.getDouble(randomID + ".y");
            double zStored = casseConfig.getDouble(randomID + ".z");
            if (x == xStored && y == yStored && z == zStored) {
                return randomID;
            }
        }
        return null;
    }
    public void addTransazione(String randomID, String autore, double prezzo, String oggetti, String data) {
        if (casseConfig.contains(randomID)) {
            ConfigurationSection cassaSection = casseConfig.getConfigurationSection(randomID + ".transazioni");
            if (cassaSection == null) {
                cassaSection = casseConfig.createSection(randomID + ".transazioni");
            }
            String newTransactionID = generateTransactionID(cassaSection);
            ConfigurationSection transactionSection = cassaSection.createSection(newTransactionID);
            transactionSection.set("autore", autore);
            transactionSection.set("prezzo", prezzo);
            transactionSection.set("oggetti", oggetti);
            transactionSection.set("data", data);
            transactionSection.set("id", generateID());
            saveFile();
        }
    }
    private String generateTransactionID(ConfigurationSection cassaSection) {
        int transactionCount = cassaSection.getKeys(false).size();
        return String.valueOf(transactionCount + 1);
    }
    public static String generateID () {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int index = rand.nextInt(ALPHABET.length());
            char letter = ALPHABET.charAt(index);
            sb.append(letter);
        }
        for (int i = 0; i < 2; i++) {
            int number = rand.nextInt(10);
            sb.append(number);
        }
        return sb.toString();
    }
    /**
     Metodi per la classe Menu (creazione GUI con pagine)
     **/
    public List<String> getTransazioni(String randomID) {
        List<String> transazioni = new ArrayList<>();
        ConfigurationSection transazioniSection = casseConfig.getConfigurationSection(randomID + ".transazioni");
        if (transazioniSection != null) {
            transazioni.addAll(transazioniSection.getKeys(false));
        }
        return transazioni;
    }
    public List<String> getTransazioniPaginate(String randomID, int page, int itemsPerPage) {
        List<String> allTransazioni = getTransazioni(randomID);
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, allTransazioni.size());
        return allTransazioni.subList(startIndex, endIndex);
    }

    public String getTransactionAutore(String randomID, String transactionID) {
        return casseConfig.getString(randomID + ".transazioni." + transactionID + ".autore", "N/D");
    }

    public double getTransactionPrezzo(String randomID, String transactionID) {
        return casseConfig.getDouble(randomID + ".transazioni." + transactionID + ".prezzo", 0.0);
    }

    public String getTransactionOggetti(String randomID, String transactionID) {
        return casseConfig.getString(randomID + ".transazioni." + transactionID + ".oggetti", "Nessun oggetto");
    }

    public String getTransactionData(String randomID, String transactionID) {
        return casseConfig.getString(randomID + ".transazioni." + transactionID + ".data", "N/D");
    }
    public String getTransactionID(String randomID, String transactionID) {
        return casseConfig.getString(randomID + ".transazioni." + transactionID + ".id", "Errore");
    }
    /**
     Fine metodi
     **/
    public void checkDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        List<String> idsToRemove = new ArrayList<>();
        for (String randomID : casseConfig.getKeys(false)) {
            ConfigurationSection transazioniSection = casseConfig.getConfigurationSection(randomID + ".transazioni");
            if (transazioniSection != null) {
                for (String transactionID : transazioniSection.getKeys(false)) {
                    String dateStr = transazioniSection.getString(transactionID + ".data");
                    if (dateStr != null) {
                        try {
                            Date transactionDate = dateFormat.parse(dateStr);
                            if (monthsDifference(currentDate, transactionDate) >= 4) {
                                idsToRemove.add(randomID + ".transazioni." + transactionID);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        for (String idToRemove : idsToRemove) {
            casseConfig.set(idToRemove, null);
        }
        saveFile();
    }
    private int monthsDifference(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);
        int diffYear = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
        int diffMonth = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
        int diffDay = calendar1.get(Calendar.DAY_OF_MONTH) - calendar2.get(Calendar.DAY_OF_MONTH);
        return diffYear * 12 + diffMonth + (diffDay < 0 ? -1 : 0);
    }
}
