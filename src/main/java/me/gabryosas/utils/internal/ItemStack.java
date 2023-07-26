package me.gabryosas.utils.internal;

import org.bukkit.Material;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ItemStack {
    /**
     Metodo che può tornare utile per creare un ItemStack.
     **/
    public static org.bukkit.inventory.ItemStack createCostumItem(Material material, String name, int modeldata, List<String> lore, int amount){
        org.bukkit.inventory.ItemStack itemStack = new org.bukkit.inventory.ItemStack(material);
        itemStack.setAmount(amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setCustomModelData(modeldata);
        itemMeta.setLore(lore);
        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
