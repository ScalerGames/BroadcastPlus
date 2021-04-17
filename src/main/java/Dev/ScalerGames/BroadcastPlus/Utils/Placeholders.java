package Dev.ScalerGames.BroadcastPlus.Utils;

import Dev.ScalerGames.BroadcastPlus.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class Placeholders extends PlaceholderExpansion {

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String getAuthor() {
        return "ScalerGames";
    }

    @Override
    public String getIdentifier() {
        return "broadcastplus";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String identifier) {

        if (identifier.equals("prefix")) {
            return Main.getInstance().getConfig().getString("BroadcastPrefix");
        }

        return "";
    }

}
