package Dev.ScalerGames.BroadcastPlus.Methods;

import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import org.bukkit.entity.Player;

public class Title {

    public static void sendTitle(String message, Player player) {
        player.sendTitle(Format.placeholder(player, message), null,
                Main.getInstance().getConfig().getInt("TitleFadeIn"),
                Main.getInstance().getConfig().getInt("TitleDisplayTime"),
                Main.getInstance().getConfig().getInt("TitleFadeOut"));
    }


}
