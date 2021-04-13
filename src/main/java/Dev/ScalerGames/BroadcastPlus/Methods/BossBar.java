package Dev.ScalerGames.BroadcastPlus.Methods;

import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;

public class BossBar {

    //Implemented in version 2.5.0

    private int taskID = -1;
    private final Main plugin;
    private org.bukkit.boss.BossBar bar;

    public BossBar(Main plugin) {
        this.plugin = plugin;
    }

    public void createBar(Integer time, String color, String style, String message) {
        try {
            bar = Bukkit.createBossBar(Format.color(message), BarColor.valueOf(color), BarStyle.valueOf(style));
        } catch (Exception e) {
            bar.setColor(BarColor.valueOf(Main.getInstance().getConfig().getString("BossBar.Color")));
            bar.setStyle(BarStyle.valueOf(Main.getInstance().getConfig().getString("BossBar.Style")));
        }
        bar.setVisible(true);
        run(time);
    }

    public void addPlayer(Player p) {
        bar.addPlayer(p);
    }

    public void run(Integer timeAmount) {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            double progress = 1.0;
            double time = 1.0/(timeAmount);

            @Override
            public void run() {
                if (progress <= 0) {
                    bar.removeAll();
                    bar.hide();
                    bar = null;
                    Bukkit.getScheduler().cancelTask(taskID);
                } else {
                    bar.setProgress(progress);
                    progress = progress - time;
                }
            }

        }, 0, 20);
    }

}
