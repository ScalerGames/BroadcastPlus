package Dev.ScalerGames.BroadcastPlus.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Format {

    public static String color(String msg) {
        if (Bukkit.getVersion().contains("1.16")) {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String placeholder(Player p, String msg) {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            msg = PlaceholderAPI.setPlaceholders(p, msg);

        }

        if (Bukkit.getVersion().contains("1.16")) {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher match = pattern.matcher(msg);
            while (match.find()) {
                String color = msg.substring(match.start(), match.end());
                msg = msg.replace(color, ChatColor.of(color) + "");
                match = pattern.matcher(msg);
            }
        }

        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String stripColor(String msg) {

        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
        Matcher match = pattern.matcher(msg);
        while (match.find()) {
            msg = msg.replace(msg.substring(match.start(), match.end()), "");
        }

        return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', msg));

    }

}
