package me.gabryosas.utils.objects;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Cassa {
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.oggetti.cassa.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.oggetti.cassa.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.oggetti.cassa.Custom-Model-Data");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.oggetti.cassa.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
