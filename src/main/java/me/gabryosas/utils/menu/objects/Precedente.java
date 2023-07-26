package me.gabryosas.utils.menu.objects;

import me.gabryosas.IthacaAziende;
import me.gabryosas.utils.internal.Color;
import org.bukkit.Material;

import java.util.List;

public class Precedente {
    /**
     Tutte le classi contenute nel pacchetto objects servono per prendere determinati dati dal config.
     Mi scuso se il codice non può sembrare 100% ottimale ma io non lavoro mai con il config.
     **/
    public static String getName(){
        return Color.translateHexColorCodes(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Precedente.Name"));
    }
    public static Material getMaterial(){
        return Material.valueOf(IthacaAziende.plugin.getConfig().getString("Items.ispeziona.Precedente.Item"));
    }
    public static int getSlot(){
        return IthacaAziende.plugin.getConfig().getInt("Items.ispeziona.Precedente.Slot");
    }
    public static int getModelData(){
        return IthacaAziende.plugin.getConfig().getInt("Items.ispeziona.Precedente.Custom-Model-Data");
    }
    public static List<String> getLore() {
        List<String> lore = IthacaAziende.plugin.getConfig().getStringList("Items.ispeziona.Precedente.Lore");
        for (int i = 0; i < lore.size(); i++) {
            String translated = Color.translateHexColorCodes(lore.get(i));
            lore.set(i, translated);
        }
        return lore;
    }
}
