package me.jaden.jumpboost.inventory.listener;

import me.jaden.jumpboost.inventory.api.Button;
import me.jaden.jumpboost.inventory.api.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof Menu)) {
            return;
        }

        Menu menu = (Menu) event.getInventory().getHolder();
        Button button = menu.getButtons().get(event.getSlot());

        if (button != null) {
            button.getListener().accept(event);
        }

        menu.refreshSlot(event.getSlot());
        if (event.getWhoClicked() instanceof Player) {
            ((Player) event.getWhoClicked()).updateInventory();
        }
    }
}
