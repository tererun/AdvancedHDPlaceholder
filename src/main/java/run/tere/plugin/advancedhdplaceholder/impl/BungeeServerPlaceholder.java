package run.tere.plugin.advancedhdplaceholder.impl;

import com.gmail.filoghost.holographicdisplays.api.placeholder.PlaceholderReplacer;

public class BungeeServerPlaceholder implements PlaceholderReplacer {

    private String serverName;

    public BungeeServerPlaceholder(String serverName) {
        this.serverName = serverName;
    }

    @Override
    public String update() {
        return null;
    }

}
