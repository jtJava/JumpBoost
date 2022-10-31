package me.jaden.jumpboost.inventory.api;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class Menu implements InventoryHolder {
    private final Player viewer;
    private final Inventory inventory;
    private final Map<Integer, Button> buttons = new HashMap<>();

    public Menu(Player viewer, int rows) {
        this.viewer = viewer;
        this.inventory = Bukkit.createInventory(this, Math.max(rows * 9, 54));
    }

    public void open() {
        for (Map.Entry<Integer, Button> entry : buttons.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItem());
        }
        viewer.openInventory(getInventory());
    }

    @Override
    @NotNull
    public Inventory getInventory() {
        return inventory;
    }

    public Map<Integer, Button> getButtons() {
        return buttons;
    }

    public void refreshSlot(int slot) {
        inventory.setItem(slot, buttons.get(slot).getItem());
    }
}
