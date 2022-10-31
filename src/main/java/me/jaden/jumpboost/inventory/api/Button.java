package me.jaden.jumpboost.inventory.api;

import java.util.function.Consumer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Button {
    private final ItemStack item;
    private final Consumer<InventoryClickEvent> listener;

    public Button(ItemStack item, Consumer<InventoryClickEvent> listener) {
        this.item = item;
        this.listener = listener;
    }

    public Button(ItemStack item) {
        this.item = item;
        this.listener = (e) -> e.setCancelled(true);
    }

    public ItemStack getItem() {
        return item;
    }

    public Consumer<InventoryClickEvent> getListener() {
        return listener;
    }
}
