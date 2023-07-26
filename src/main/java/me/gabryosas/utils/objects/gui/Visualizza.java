package me.gabryosas.utils.objects.gui;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Visualizza {
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.cassa-gui.Items.visualizza-transazioni.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.cassa-gui.Items.visualizza-transazioni.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.cassa-gui.Items.visualizza-transazioni.Custom-Model-Data");
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.cassa-gui.Items.visualizza-transazioni.Slot");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.cassa-gui.Items.visualizza-transazioni.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
