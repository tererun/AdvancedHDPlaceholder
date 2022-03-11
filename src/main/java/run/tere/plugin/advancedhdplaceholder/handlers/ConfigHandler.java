package run.tere.plugin.advancedhdplaceholder.handlers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigHandler {

    private FileConfiguration config;
    private String statusOnline;
    private String statusOffline;

    public ConfigHandler(Plugin plugin) {
        plugin.saveDefaultConfig();
        this.config = plugin.getConfig();
    }

    private void init() {
        this.statusOnline = config.getString("status.online");
        this.statusOffline = config.getString("status.offline");
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public String getStatusOnline() {
        return statusOnline;
    }

    public String getStatusOffline() {
        return statusOffline;
    }
}
