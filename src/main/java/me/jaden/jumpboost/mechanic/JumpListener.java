package me.jaden.jumpboost.mechanic;

import me.jaden.jumpboost.database.DataManager;
import me.jaden.jumpboost.database.JumpProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class JumpListener implements Listener {
    private static final String JUMP_BOOST_PERMISSION = "jumpboost.boost";

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission(JUMP_BOOST_PERMISSION)) {
            event.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onJump(PlayerToggleFlightEvent event) {
        if (!event.getPlayer().hasPermission(JUMP_BOOST_PERMISSION)) {
            return;
        }
        Player player = event.getPlayer();
        JumpProfile profile = DataManager.getInstance().getPlayerData(player.getUniqueId());

        Vector velocity = event.getPlayer().getVelocity();
        velocity.setY((velocity.getY() + .2) * profile.getJumpLevel());

        player.setVelocity(velocity);
        event.setCancelled(true);
    }
}
