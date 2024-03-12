package dev.kefany.utils.inventory;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;

public final class InventoryUtils
{
    public static List<ItemStack> getItemsFromPlayerInventory(int from, final int to, final Player player) {
        final List<ItemStack> items = new ArrayList<ItemStack>();
        while (from <= to) {
            final ItemStack itemInInventory = player.getInventory().getItem(from);
            ItemStack addedItem;
            if (itemInInventory == null || itemInInventory.getType().equals(Material.AIR)) {
                addedItem = new ItemStack(Material.AIR);
            }
            else {
                addedItem = itemInInventory;
            }
            items.add(addedItem);
            ++from;
        }
        return items;
    }
    
    private InventoryUtils() {
    }
}
