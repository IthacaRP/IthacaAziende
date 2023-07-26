package me.gabryosas.utils.menu.objects;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Scontrini {
    public static String getName(String autore, String oggetti, String data, String id, int prezzo){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Scontrini.Name").replace("%autore%", autore).replace("%oggetti%", oggetti).replace("%data%", data).replace("%id%", id).replace("%prezzo%", String.valueOf(prezzo)));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Scontrini.Item"));
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.ispeziona.Scontrini.Custom-Model-Data");
    }
    public static List<String> getLore(String autore, String oggetti, String data, String id, int prezzo) {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.ispeziona.Scontrini.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i).replace("%autore%", autore).replace("%oggetti%", oggetti).replace("%data%", data).replace("%id%", id).replace("%prezzo%", String.valueOf(prezzo)));
            lore.set(i, translated);
        }
        return lore;
    }
}
