package me.jaden.jumpboost.database;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;


public class DataManager implements Listener {
    private static DataManager instance;

    private final Map<UUID, JumpProfile> playerData = new HashMap<>();

    public DataManager() {
        instance = this;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(AsyncPlayerPreLoginEvent event) {
        if (event.getLoginResult() == AsyncPlayerPreLoginEvent.Result.ALLOWED) {
            addPlayerData(event.getUniqueId());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        JumpProfile profile = this.removePlayerData(event.getPlayer().getUniqueId());
        try {
            profile.getSaveStatement().executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JumpProfile getPlayerData(UUID uuid) {
        return this.playerData.get(uuid);
    }

    public void addPlayerData(UUID uuid) {
        this.playerData.put(uuid, new JumpProfile().load(uuid));
    }

    public JumpProfile removePlayerData(UUID uuid) {
        return this.playerData.remove(uuid);
    }

    public static DataManager getInstance() {
        return instance;
    }
}
