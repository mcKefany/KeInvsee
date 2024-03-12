package dev.kefany.models.GUI;

import java.util.List;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import dev.kefany.utils.chat.ChatUtils;

public final class GUIItem
{
    private final ItemStack viewItem;
    private final String displayName;
    private final List<String> lore;
    private final List<Integer> slots;
    
    public GUIItem(final String materialName, final String displayName, final List<String> lore, final List<Integer> slots) {
        this.viewItem = new ItemStack(Material.valueOf(materialName.toUpperCase()));
        this.displayName = ChatUtils.format(displayName);
        this.lore = ChatUtils.format(lore);
        this.slots = slots;
    }
    
    public ItemStack convertToBukkitItemStack() {
        final ItemStack convertedItem = this.viewItem.clone();
        final ItemMeta convertedMeta = convertedItem.getItemMeta();
        convertedMeta.setDisplayName(this.displayName);
        convertedMeta.setLore(this.lore);
        convertedItem.setItemMeta(convertedMeta);
        return convertedItem;
    }
    
    public void setInInventory(final Inventory inventory) {
        this.slots.forEach(slot -> inventory.setItem(slot, this.convertToBukkitItemStack()));
    }
}
