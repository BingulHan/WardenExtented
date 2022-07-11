package online.bingulhan.wardenextented;

import online.bingulhan.wardenextented.listeners.WardenListener;
import org.bukkit.entity.Warden;
import org.bukkit.plugin.java.JavaPlugin;

public final class WardenExtented extends JavaPlugin {

    public static Warden CURRENT_WARDEN = null;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WardenListener(), this);
        getCommand("givewei").setExecutor(new CMDGiveItem());
    }

    @Override
    public void onDisable() {

    }
}
