package me.gabryosas.utils.objects.gui;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Fondi {
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.cassa-gui.Items.gestisci-fondi.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.cassa-gui.Items.gestisci-fondi.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.cassa-gui.Items.gestisci-fondi.Custom-Model-Data");
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.cassa-gui.Items.gestisci-fondi.Slot");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.cassa-gui.Items.gestisci-fondi.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
