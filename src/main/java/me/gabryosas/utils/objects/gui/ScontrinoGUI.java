package me.gabryosas.utils.objects.gui;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class ScontrinoGUI {
    public static String getName1(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Name-1"));
    }
    public static int getDistance(){
        return IthacaAziende.plugin.getConfig().getInt("Items.scontrino-gui.Distance");
    }
    public static int getMaxSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.scontrino-gui.Max-Slot");
    }
    public static String getPrefixColor(){
        return IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Player-GUI.Prefix-Color");
    }
    public static List<String> getLorePlayer() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.scontrino-gui.Player-GUI.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
    public static String getName2(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Name-2"));
    }
    public static String getName3(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Name-3"));
    }
    //Sezione scontrino items
    public static String getName(String nome){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Scontrino.Name").replace("%azienda%", nome));
    }
    public static Material getMaterialScontrino(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.scontrino-gui.Scontrino.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.scontrino-gui.Scontrino.Custom-Model-Data");
    }
    public static List<String> getLore(String data, String cliente, String autore, int prezzo, String oggetti) {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.scontrino-gui.Scontrino.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i).replace("%data%", data).replace("%cliente%", cliente).replace("%autore%", autore).replace("%prezzo%", String.valueOf(prezzo)).replace("%oggetti%", oggetti));
            lore.set(i, translated);
        }
        return lore;
    }
}
