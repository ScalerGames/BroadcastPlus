package Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting;

import Dev.ScalerGames.BroadcastPlus.Files.Lang;
import Dev.ScalerGames.BroadcastPlus.Main;
import Dev.ScalerGames.BroadcastPlus.Methods.ActionBar;
import Dev.ScalerGames.BroadcastPlus.Methods.Gui.GuiCreator;
import Dev.ScalerGames.BroadcastPlus.Methods.Title;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import Dev.ScalerGames.BroadcastPlus.Utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalBroadcast implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("localbroadcast") || label.equalsIgnoreCase("lb")) {

            if (s instanceof Player) {

                Player p = (Player) s;
                if (p.hasPermission("bp.localbroadcast")) {

                    if (args.length >= 2) {

                        if (args[0].equalsIgnoreCase("chat")) {
                            if (Util.isInt(args[1])) {
                                if (Broadcast.presets.contains(args[2])) {
                                    for (Player player : Bukkit.getOnlinePlayers()) {
                                        if (player.getLocation().distance(player.getLocation()) <= Integer.parseInt(args[1])) {
                                            for (String msg : Main.getInstance().getConfig().getStringList("Presets." + args[2] + ".message")) {
                                                player.sendMessage(Format.placeholder(player, msg));
                                            }
                                        }
                                    }
                                } else {
                                    for (Player player : Bukkit.getOnlinePlayers()) {
                                        if (player.getLocation().distance(player.getLocation()) <= Integer.parseInt(args[1])) {
                                            player.sendMessage(Format.placeholder(player, Main.getInstance().getConfig().getString("BroadcastPrefix") + "&r " + Util.stringJoin(args, 2)));
                                        }
                                    }
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Distance"));
                            }
                        }

                        if (args[0].equalsIgnoreCase("bar")) {
                            if (args.length >= 3) {
                                if (Util.isInt(args[1])) {
                                    if (Util.isInt(args[2])) {
                                        for (Player player : Bukkit.getOnlinePlayers()) {
                                            if (player.getLocation().distance(player.getLocation()) <= Integer.parseInt(args[1])) {
                                                ActionBar.sendActionBar(player, Util.stringJoin(args, 3), Integer.parseInt(args[2]));
                                            }
                                        }
                                    } else {
                                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Timing"));
                                    }
                                } else{
                                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Region"));
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("local-broadcast-bar-usage")));
                            }
                        }

                        if (args[0].equalsIgnoreCase("title")) {
                            if (Util.isInt(args[1])) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    if (player.getLocation().distance(player.getLocation()) <= Integer.parseInt(args[1])) {
                                        Title.sendTitle(Util.stringJoin(args, 2), player);
                                    }
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Region"));
                            }
                        }

                        if (args[0].equalsIgnoreCase("gui")) {
                            if (args.length >= 3) {
                                if (Util.isInt(args[1])) {
                                    for (Player player : Bukkit.getOnlinePlayers()) {
                                        if (player.getLocation().distance(player.getLocation()) <= Integer.parseInt(args[1])) {
                                            GuiCreator.generate(p, args[2]);
                                        }
                                    }
                                } else {
                                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Region"));
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("local-broadcast-gui-usage")));
                            }
                        }

                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("lang-broadcast-usage")));
                    }

                }

            } else {
                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThis command is only accessible via a player"));
            }

        }

        return false;
    }

}
