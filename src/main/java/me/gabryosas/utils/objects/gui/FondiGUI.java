package me.gabryosas.utils.objects.gui;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class FondiGUI {
    public static int getMaxSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.fondi.Max-Slot");
    }
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.fondi.Name"));
    }
    public static String getNameItems(String nome, int value){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.fondi.Items.Name").replace("%azienda%", nome).replace("%soldi%", String.valueOf(value)));
    }
    public static Material getMaterialItems(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.fondi.Items.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.fondi.Items.Custom-Model-Data");
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.fondi.Items.Slot");
    }
    public static List<String> getLore(String nome, int value) {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.fondi.Items..Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i).replace("%azienda%", nome).replace("%soldi%", String.valueOf(value)));
            lore.set(i, translated);
        }
        return lore;
    }
}
