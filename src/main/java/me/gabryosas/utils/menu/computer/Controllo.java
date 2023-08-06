package me.gabryosas.utils.menu.computer;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.ArrayList;
import java.util.List;

public class Controllo {
    //Nome GUI
    public static String getName1(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.controllo.Name-1"));
    }
    public static String getName2(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.controllo.Name-2"));
    }
    //Item
    public static String getName(String autore, String azienda, String data, String id, int prezzo){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.controllo.oggetti.Controllo.Name").replace("%autore%", autore).replace("%azienda%", azienda).replace("%data%", data).replace("%id%", id).replace("%importo%", String.valueOf(prezzo)));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.controllo.oggetti.Controllo.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.controllo.oggetti.Controllo.Custom-Model-Data");
    }
    //Per un bug ho dovuto cambiare codice (di poco)
    public static List<String> getLore(String autore, String azienda, String data, String id, int prezzo) {
        FileConfiguration config = IthacaAziende.plugin.getConfig();
        List<String> lore = config.getStringList("Items.controllo.oggetti.Controllo.Lore");
        List<String> translatedLore = new ArrayList<>();
        for (String line : lore) {
            String translated = Color.translateHexColorCodes(line).replace("%autore%", autore).replace("%azienda%", azienda).replace("%data%", data).replace("%id%", id).replace("%importo%", String.valueOf(prezzo));
            translatedLore.add(translated);
        }
        return translatedLore;
    }
}

