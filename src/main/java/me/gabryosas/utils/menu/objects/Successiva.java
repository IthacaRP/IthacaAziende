package me.gabryosas.utils.menu.objects;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Successiva {
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Successiva.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Successiva.Item"));
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.ispeziona.Successiva.Slot");
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.ispeziona.Successiva.Custom-Model-Data");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.ispeziona.Successiva.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}