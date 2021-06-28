package Dev.ScalerGames.BroadcastPlus.Methods;

import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class AutoBroadcast {

    private final Main plugin;

    public AutoBroadcast(Main plugin) {
        this.plugin = plugin;
    }

    public void autoMessage() {
        if (Main.getInstance().getConfig().getBoolean("AutoBroadcast.enabled")) {
            Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    String msg = Main.getInstance().getConfig().getStringList("AutoBroadcast.messages").get(new Random().nextInt(Main.getInstance().getConfig().getStringList("AutoBroadcast.messages").size()));
                    if (msg.contains("preset:")) {
                        if (Main.getInstance().getConfig().getConfigurationSection("Presets").getKeys(false)
                                .contains(msg.replace("preset:", ""))) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                for (String line : Main.getInstance().getConfig().getStringList("Presets." + msg.replace("preset:", "") + ".message")) {
                                    p.sendMessage(Format.placeholder(p, line));
                                }
                            }
                        }
                    } else {
                        Bukkit.getConsoleSender().sendMessage(Format.color(Main.getInstance().getConfig().getString("BroadcastPrefix") + msg));
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.sendMessage(Format.placeholder(p, Main.getInstance().getConfig().getString("BroadcastPrefix") + ChatColor.RESET + msg));
                        }
                    }
                }
            }, 0L, 20L * Main.getInstance().getConfig().getInt("AutoBroadcast.time"));
        }
    }

}
