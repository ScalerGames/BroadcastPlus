package Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting;

import Dev.ScalerGames.BroadcastPlus.Files.Lang;
import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Methods.ActionBar;
import Dev.ScalerGames.BroadcastPlus.Methods.Gui.GuiCreator;
import Dev.ScalerGames.BroadcastPlus.Methods.Title;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import Dev.ScalerGames.BroadcastPlus.Utils.Util;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupBroadcast implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("groupbroadcast") || label.equalsIgnoreCase("gb")) {

            if (s.hasPermission("bp.groupbroadcast")) {

                if (args.length >= 2) {

                    if (args[0].equalsIgnoreCase("chat")) {

                        if (lpEnabled()) {
                            Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);

                            if (group == null) {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis group was not found"));
                            } else {
                                if (Broadcast.presets.contains(args[2])) {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (isPlayerInGroup(p, args[1])) {
                                            for (String msg : Main.getInstance().getConfig().getStringList("Presets." + args[2] + ".message")) {
                                                p.sendMessage(Format.placeholder(p, msg));
                                            }
                                        }
                                    }
                                } else {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (isPlayerInGroup(p, args[1])) {
                                            p.sendMessage(Format.placeholder(p, (Main.getInstance().getConfig().getString("BroadcastPrefix") + "&r " + Util.stringJoin(args, 2))));
                                        }
                                    }
                                }
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cLuckPerms is not enabled"));
                        }

                    }

                    if (args[0].equalsIgnoreCase("bar")) {

                        if (lpEnabled()) {

                            if (args.length >= 3) {

                                if (Util.isInt(args[2])) {
                                    Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);

                                    if (group == null) {
                                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis group was not found"));
                                    } else {
                                        for (Player p : Bukkit.getOnlinePlayers()) {
                                            if (isPlayerInGroup(p, args[1])) {
                                                ActionBar.sendActionBar(p, Util.stringJoin(args, 3), Integer.parseInt(args[2]));
                                            }
                                        }
                                    }
                                } else {
                                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Timing"));
                                }

                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("group-broadcast-bar-usage")));
                            }

                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cLuckPerms is not enabled"));
                        }

                    }

                    if (args[0].equalsIgnoreCase("title")) {

                        if (lpEnabled()) {

                            Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);

                            if (group == null) {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis group was not found"));
                            } else {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (isPlayerInGroup(p, args[1])) {
                                        Title.sendTitle(Util.stringJoin(args, 2), p);
                                    }
                                }
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cLuckPerms is not enabled"));
                        }

                    }

                    if (args[0].equalsIgnoreCase("gui")) {

                        if (lpEnabled()) {

                            if (args.length >= 3) {
                                Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);

                                if (group == null) {
                                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis group was not found"));
                                } else {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (isPlayerInGroup(p, args[1])) {
                                            GuiCreator.generate(p, args[2]);
                                        }
                                    }
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("group-broadcast-gui-usage")));
                            }

                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cLuckPerms is not enabled"));
                        }

                    }

                    if (args[0].equalsIgnoreCase("boss")) {
                        if (lpEnabled()) {
                            if (args.length >= 6 && Util.isInt(args[2])) {
                                Main.bar.createBar(Integer.parseInt(args[2]), args[3], args[4], Util.stringJoin(args, 5));
                                Group group = LuckPermsProvider.get().getGroupManager().getGroup(args[1]);

                                if (group == null) {
                                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis group was not found"));
                                } else {
                                    for (Player p : Bukkit.getOnlinePlayers()) {
                                        if (isPlayerInGroup(p, args[1])) {
                                            Main.bar.addPlayer(p);
                                        }
                                    }
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("group-broadcast-boss-usage")));
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cLuckPerms is not enabled"));
                        }
                    }

                } else {
                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("group-broadcast-usage")));
                }

            } else {
                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-permission")));
            }

        }

        return false;
    }

    public static boolean lpEnabled() {
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            return true;
        }
        return false;
    }

    public static boolean isPlayerInGroup(Player p, String group) {
        return p.hasPermission("group." + group);
    }

}
