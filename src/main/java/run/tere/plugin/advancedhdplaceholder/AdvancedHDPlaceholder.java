package run.tere.plugin.advancedhdplaceholder;

import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import run.tere.plugin.advancedhdplaceholder.handlers.ConfigHandler;
import run.tere.plugin.advancedhdplaceholder.impl.BungeeServerPlaceholder;

public final class AdvancedHDPlaceholder extends JavaPlugin implements PluginMessageListener {

    private static AdvancedHDPlaceholder instance;
    private ConfigHandler configHandler;

    @Override
    public void onEnable() {
        instance = this;
        if (!getServer().getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().severe("HolographicDisplaysが有効化されていないため起動ができませんでした");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
        sendGetServersPluginMessage();
    }

    private void sendGetServersPluginMessage() {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("GetServers");
        getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subChannel = in.readUTF();
        if (subChannel.equals("GetServers")) {
            String[] serverList = in.readUTF().split(", ");
            for (String server : serverList) {
                HologramsAPI.registerPlaceholder(this, "bungee_online: " + server, 30, new BungeeServerPlaceholder(server));
            }
        }
    }
}
