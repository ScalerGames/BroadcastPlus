package Dev.ScalerGames.BroadcastPlus.Methods.Gui;

import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GuiListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (GuiCreator.list.contains(ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', event.getView().getTitle())))) {
            event.setCancelled(true);
        }
    }

}
