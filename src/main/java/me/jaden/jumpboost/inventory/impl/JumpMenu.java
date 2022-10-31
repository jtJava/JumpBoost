package me.jaden.jumpboost.inventory.impl;

import me.jaden.jumpboost.database.DataManager;
import me.jaden.jumpboost.inventory.api.Button;
import me.jaden.jumpboost.inventory.api.Menu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JumpMenu extends Menu {
    public JumpMenu(Player viewer) {
        super(viewer, 1);
        for (int i = 0; i < 8; i++) {
            this.getButtons().put(i, new Button(new ItemStack(Material.GLASS_PANE)));
        }

        this.getButtons().put(3, new Button(new ItemStack(Material.ARROW), inventoryClickEvent -> {
            DataManager.getInstance().getPlayerData(inventoryClickEvent.getWhoClicked().getUniqueId()).decrementJumpLevel();
            inventoryClickEvent.setCancelled(true);
        }));

        ItemStack display = new ItemStack(Material.PAPER);
        ItemMeta newMeta = display.getItemMeta();
        newMeta.setDisplayName("Jump Level: " + DataManager.getInstance().getPlayerData(viewer.getUniqueId()).getJumpLevel());
        display.setItemMeta(newMeta);

        this.getButtons().put(4, new Button(display));

        this.getButtons().put(5, new Button(new ItemStack(Material.ARROW), inventoryClickEvent -> {
            DataManager.getInstance().getPlayerData(inventoryClickEvent.getWhoClicked().getUniqueId()).incrementJumpLevel();
            inventoryClickEvent.setCancelled(true);
        }));
    }
}
