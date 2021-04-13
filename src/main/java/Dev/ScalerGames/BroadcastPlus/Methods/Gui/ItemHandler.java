package Dev.ScalerGames.BroadcastPlus.Methods.Gui;

import Dev.ScalerGames.BroadcastPlus.Files.Gui;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import Dev.ScalerGames.BroadcastPlus.Utils.Util;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemHandler {

    public static void item(Inventory inv, String name, String slot, Player p) {

        String itemName = slot.substring(slot.lastIndexOf(".") + 1);

        try {

            ItemStack item = new ItemStack(Material.valueOf(Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".item")));
            ItemMeta meta = item.getItemMeta();

            displayName(meta, p, name, itemName);
            lore(meta, p, name, itemName);
            enchants(meta, p, name, itemName);
            flags(meta, p, name, itemName);

            item.setItemMeta(meta);


            try {
                if (Gui.getGuiConfig().isInt("Menus." + name + ".items." + itemName + ".slot")) {
                    inv.setItem(Gui.getGuiConfig().getInt("Menus." + name + ".items." + itemName + ".slot"), item);
                }

                if (Gui.getGuiConfig().isString("Menus." + name + ".items." + itemName + ".slot")) {
                    String[] list = Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".slot").split("-");
                    if (list.length == 0) {
                        inv.setItem(Integer.parseInt(Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".slot")), item);
                    }
                    else {
                        String from = list[0];
                        String too = list[1];
                        for (Integer i = Integer.parseInt(from); i <= Integer.parseInt(too); i++) {
                            inv.setItem(i, item);
                        }
                    }
                }

                if (Gui.getGuiConfig().isList("Menus." + name + ".items." + itemName + ".slot")) {
                    for (String integer : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemName + ".slot")) {
                        String[] list = integer.split("-");
                        if (list.length == 0) {
                            inv.setItem(Integer.parseInt(integer), item);
                        } else {
                            String from = list[0];
                            String too = list[1];
                            for (Integer i = Integer.parseInt(from); i <= Integer.parseInt(too); i++) {
                                inv.setItem(i, item);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                Util.logger("&cAn item could not find it's slot in the GUI");
            }

        } catch (Exception e) {
            ItemStack item = new ItemStack(Material.BARRIER);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(Format.color("&4&lInvalid Item"));
            item.setItemMeta(meta);
            inv.setItem(Gui.getGuiConfig().getInt("Menus." + name + ".items." + itemName + ".slot"), item);
            Util.logger("Invalid Item In " + p.getName() + "'s Open GUI");
        }

    }

    //Display name Handler
    public static void displayName(ItemMeta meta, Player p, String name, String itemName) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".display-name")) {
            meta.setDisplayName(Format.placeholder(p, Gui.getGuiConfig().getString("Menus." + name + ".items." + itemName + ".display-name")));
        }
    }

    //Lore Handler
    public static void lore(ItemMeta meta, Player p, String name, String itemName) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".lore")) {
            List<String> lore = new ArrayList<String>();
            for (String list : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemName + ".lore")) {
                lore.add(Format.placeholder(p, list));
            }
            meta.setLore(lore);
        }
    }

    //Enchants Handler
    public static void enchants(ItemMeta meta, Player p, String name, String itemName) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".enchants")) {

            for (String enchants : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemName + ".enchants")) {
                String[] split = enchants.split(":");
                String enchant = split[0];
                int level = Integer.parseInt(split[1]);

                try {
                    meta.addEnchant(Enchantment.getByName(enchant), level, true);
                } catch (Exception e) {
                    Util.logger("&cInvalid Enchant in " + p.getName() + "'s open GUI");
                }
            }

        }
    }

    public static void flags(ItemMeta meta, Player p, String name, String itemName) {
        if (Gui.getGuiConfig().contains("Menus." + name + ".items." + itemName + ".flags")) {

            for (String flags : Gui.getGuiConfig().getStringList("Menus." + name + ".items." + itemName + ".flags")) {
                try {
                    meta.addItemFlags(ItemFlag.valueOf(flags));
                } catch (Exception e) {
                    Util.logger("&cInvalid flag in " + p.getName() + "'s open GUI");
                }
            }

        }
    }

}
