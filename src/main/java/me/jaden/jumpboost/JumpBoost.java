package me.jaden.jumpboost;

import me.jaden.jumpboost.command.JumpCommand;
import me.jaden.jumpboost.database.DataManager;
import me.jaden.jumpboost.database.MySQL;
import me.jaden.jumpboost.inventory.listener.MenuListener;
import me.jaden.jumpboost.mechanic.JumpListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class JumpBoost extends JavaPlugin {
    private static JumpBoost plugin;

    public static JumpBoost getPlugin() {
        return plugin;
    }

    private MySQL mySQL;

    @Override
    public void onEnable() {
        plugin = this;

        // Plugin startup logic
        this.mySQL = new MySQL();

        this.getServer().getPluginManager().registerEvents(new JumpListener(), this);
        this.getServer().getPluginManager().registerEvents(new MenuListener(), this);
        this.getServer().getPluginManager().registerEvents(new DataManager(), this);

        this.getCommand("jump").setExecutor(new JumpCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.mySQL.disconnect();
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
