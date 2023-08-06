package me.gabryosas.utils.menu.computer;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Precedente {
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.controllo.oggetti.Precedente.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.controllo.oggetti.Precedente.Item"));
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.controllo.oggetti.Precedente.Slot");
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.controllo.oggetti.Precedente.Custom-Model-Data");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.controllo.oggetti.Precedente.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
