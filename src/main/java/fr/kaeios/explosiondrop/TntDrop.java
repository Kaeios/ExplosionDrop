package fr.kaeios.explosiondrop;

import fr.kaeios.explosiondrop.listeners.TntExplodeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class TntDrop extends JavaPlugin {

    @Override
    public void onEnable() {
        // Load configuration & set default if used for the first time
        saveDefaultConfig();
        // Register plugin listeners
        final PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new TntExplodeListener(getConfig()), this);
    }

}
