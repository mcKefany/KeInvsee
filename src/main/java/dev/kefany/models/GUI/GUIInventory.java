package dev.kefany.models.GUI;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;

import dev.kefany.utils.inventory.InventoryUtils;
import dev.kefany.utils.chat.ChatUtils;
import dev.kefany.managers.config.ConfigManager;
import dev.kefany.providers.GUI.GUIItemsProvider;

public final class GUIInventory
{
    private final List<GUIItem> GUIItems;
    private final Player whoWatching;
    private final Player whoViewing;
    private final String title;
    private final int size;
    
    public GUIInventory(final Player whoWatching, final Player whoViewing) {
        this.GUIItems = GUIItemsProvider.getGUIItems();
        this.whoWatching = whoWatching;
        this.whoViewing = whoViewing;
        this.title = ChatUtils.format(ConfigManager.getDefaultConfig().getString("GUI_VIEW.TITLE").replaceAll("%player_name%", whoViewing.getName()));
        this.size = 45;
    }
    
    public Inventory convertToBukkitInventory() {
        final Inventory inventory = Bukkit.createInventory(this.whoWatching, this.size, this.title);
        this.GUIItems.forEach(GUIItem -> GUIItem.setInInventory(inventory));
        inventory.setItem(2, this.whoViewing.getInventory().getHelmet());
        inventory.setItem(3, this.whoViewing.getInventory().getChestplate());
        inventory.setItem(4, this.whoViewing.getInventory().getLeggings());
        inventory.setItem(5, this.whoViewing.getInventory().getBoots());
        inventory.setItem(6, this.whoViewing.getInventory().getItemInOffHand());
        final List<ItemStack> activeRow = InventoryUtils.getItemsFromPlayerInventory(0, 8, this.whoViewing);
        final List<ItemStack> firstRow = InventoryUtils.getItemsFromPlayerInventory(27, 35, this.whoViewing);
        final List<ItemStack> secondRow = InventoryUtils.getItemsFromPlayerInventory(18, 26, this.whoViewing);
        final List<ItemStack> thirdRow = InventoryUtils.getItemsFromPlayerInventory(9, 17, this.whoViewing);
        final List<ItemStack> allItems = new ArrayList<ItemStack>();
        allItems.addAll(activeRow);
        allItems.addAll(firstRow);
        allItems.addAll(secondRow);
        allItems.addAll(thirdRow);
        this.setInInventoryItems(allItems, 0, 9, 17, inventory);
        this.setInInventoryItems(allItems, 9, 18, 26, inventory);
        this.setInInventoryItems(allItems, 18, 27, 35, inventory);
        this.setInInventoryItems(allItems, 27, 36, 44, inventory);
        return inventory;
    }
    
    private void setInInventoryItems(final List<ItemStack> items, int indexElement, int indexSlot, final int maxSlot, final Inventory inventory) {
        while (indexSlot <= maxSlot && items.size() > indexElement) {
            inventory.setItem(indexSlot, items.get(indexElement));
            ++indexElement;
            ++indexSlot;
        }
    }
}
