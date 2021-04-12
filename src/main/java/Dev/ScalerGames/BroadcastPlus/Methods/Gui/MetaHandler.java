package Dev.ScalerGames.BroadcastPlus.Methods.Gui;

import Dev.ScalerGames.BroadcastPlus.Files.Gui;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;

import java.lang.reflect.Field;
import java.util.UUID;

public class MetaHandler {

    public static ItemMeta displayName(ItemMeta meta, Player p, String name, String itemName) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".display-name")) {
            meta.setDisplayName(Format.placeholder(p, Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".display-name")));
        }
        return meta;
    }

    public static void head(ItemStack item, Player p, String name, String itemName) {
        if (item.getType().equals(Material.PLAYER_HEAD)) {
            if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".head")) {
                SkullMeta meta = (SkullMeta) item.getItemMeta();
                GameProfile profile = new GameProfile(UUID.randomUUID(), "");
                profile.getProperties().put("texture", new Property("texture", Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".head")));
                Field profileField = null;
                try {
                    profileField = meta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(meta, profile);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    meta.setDisplayName(Format.placeholder(p, "&4&lInvalid Base64 Head"));
                }
                item.setItemMeta(meta);
            }
        }
    }

}
