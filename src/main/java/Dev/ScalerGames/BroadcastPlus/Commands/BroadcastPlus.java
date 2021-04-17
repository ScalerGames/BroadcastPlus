package Dev.ScalerGames.BroadcastPlus.Commands;

import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.Broadcast;
import Dev.ScalerGames.BroadcastPlus.Files.Gui;
import Dev.ScalerGames.BroadcastPlus.Files.Lang;
import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Methods.Gui.GuiCreator;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import Dev.ScalerGames.BroadcastPlus.Utils.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastPlus implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("broadcastplus") || label.equalsIgnoreCase("bp")) {

            if (args.length == 0) {
                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&3This server is running version "
                        + Main.getInstance().getDescription().getVersion() + " of BroadcastPlus made by ScalerGames"));
            }

            if (args.length >= 1) {

                if (args[0].equalsIgnoreCase("version")) {
                    if (s.hasPermission("bp.version")) {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&9The server is running version "
                        + Main.getInstance().getDescription().getVersion() + " of BroadcastPlus"));
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-plus-permission")));
                    }
                }

                if (args[0].equalsIgnoreCase("reload")) {
                    if (s.hasPermission("bp.reload")) {
                        Main.getInstance().reloadConfig();
                        Gui.reloadGui();
                        Lang.reloadLang();
                        Util.logger("&2Successfully reloaded the config.yml");
                        Util.logger("&2Successfully reloaded the lang.yml");
                        Util.logger("&2Successfully reloaded the gui.yml");
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&9Successfully reloaded all BroadcastPlus's files"));
                        for (String menus : Gui.getGuiConfig().getConfigurationSection("Menus").getKeys(false)) {
                            if (!GuiCreator.list.contains(Format.stripColor(Gui.getGuiConfig().getString("Menus." + menus.substring(menus.lastIndexOf(".") + 1) + ".name")))) {
                                GuiCreator.list.add(Format.stripColor(Gui.getGuiConfig().getString("Menus." + menus.substring(menus.lastIndexOf(".") + 1) + ".name")));
                            }
                        }
                        for (String preset : Main.getInstance().getConfig().getConfigurationSection("Presets").getKeys(false)) {
                            if (!Broadcast.presets.contains(Main.getInstance().getConfig().getConfigurationSection("Presets." + preset.substring(preset.lastIndexOf(".") + 1)).getName())) {
                                Broadcast.presets.add(Main.getInstance().getConfig().getConfigurationSection("Presets." + preset.substring(preset.lastIndexOf(".") + 1)).getName());
                            }
                        }
                        Util.logger("&3GUI List: " + GuiCreator.list.toString() + " Preset List: " + Broadcast.presets.toString());
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-plus-permission")));
                    }
                }

                if (args[0].equalsIgnoreCase("help")) {
                    if (s.hasPermission("bp.help")) {
                        s.sendMessage(Format.color("&3&lBroadcast: &9/broadcast [method] [message]"));
                        s.sendMessage(Format.color("&3&lBroadcastWorld: &9/broadcastworld [method] [world] [message]"));
                        s.sendMessage(Format.color("&3&lLocalBroadcast: &9/localbroadcast [method] [range] [message]"));
                        s.sendMessage(Format.color("&3&lGroupBroadcast: &9/groupbroadcast [method] [group] [message]"));
                        s.sendMessage(Format.color("&3&lMethods: &9Chat, Title, Bar, GUI, Boss"));
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-plus-permission")));
                    }
                }

                if (args[0].equalsIgnoreCase("wiki")) {
                    if (s.hasPermission("bp.wiki")) {
                        s.sendMessage(Format.color("&3&lWiki: &9https://github.com/ScalerGames/BroadcastPlus/wiki"));
                    }
                }

                if (!(args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("wiki"))) {
                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cUsage: /" + label + " [reload|version]"));
                }

            }

        }
        return false;
    }

}
