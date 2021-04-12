package Dev.ScalerGames.BroadcastPlus.Methods;

import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendActionBar(Player player, String message, int seconds) {

        int task = 0;
        int finalTask = task;
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {

            int time = 0;

            @Override
            public void run() {
                if (time == seconds) {
                    Bukkit.getScheduler().cancelTask(finalTask);
                } else {
                    time++;
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Format.placeholder(player, message)));
                }
            }
        }, 0, 20*1);

    }

}
