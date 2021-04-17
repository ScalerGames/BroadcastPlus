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

public class BroadcastWorld implements CommandExecutor {

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("broadcastworld") || label.equalsIgnoreCase("bw")) {

            if (s.hasPermission("bp.broadcastworld")) {
                if (args.length <= 1) {
                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-world-usage")));
                }
            }

            if (args.length >= 2) {

                String prefix = Main.getInstance().getConfig().getString("BroadcastPrefix");

                if (args[0].equalsIgnoreCase("chat")) {
                    if (Main.worlds.contains(args[1].toLowerCase())) {
                        if (Broadcast.presets.contains(args[2])) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    for (String msg : Main.getInstance().getConfig().getStringList("Presets." + args[2] + ".message")) {
                                        p.sendMessage(Format.placeholder(p, msg));
                                    }
                                }
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    p.sendMessage(Format.placeholder(p, Main.getInstance().getConfig().getString("BroadcastPrefix") + Util.stringJoin(args, 2)));
                                }
                            }
                        }
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThat world is not recognized"));
                    }
                }

                if (args[0].equalsIgnoreCase("bar")) {
                    if (args.length >= 3) {
                        if (Util.isInt(args[2])) {
                            if (Main.worlds.contains(args[1].toLowerCase())) {
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                        ActionBar.sendActionBar(p, Util.stringJoin(args, 3), Integer.parseInt(args[2]));
                                    }
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cThat world is not recognized"));
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Time"));
                        }
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-world-bar-usage")).replace("{cmd}", label));
                    }
                }

                if (args[0].equalsIgnoreCase("title")) {
                    if (Main.worlds.contains(args[1].toLowerCase())) {
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                Title.sendTitle(Util.stringJoin(args, 2), p);
                            }
                        }
                    }
                }

                if (args[0].equalsIgnoreCase("help")) {
                    s.sendMessage(Format.color("&8&l&m======&cBroadcast World&8&l&m======"));
                    s.sendMessage(Format.color("&cLoaded Worlds: " + Main.worlds.toString()));
                    s.sendMessage(Format.color("&cUsage: /{cmd} [chat|bar|title] [world] [message]").replace("{cmd}", label));
                }

                if (args[0].equalsIgnoreCase("gui")) {
                    if (args.length == 3) {
                        if (Main.worlds.contains(args[1].toLowerCase())) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    GuiCreator.generate(p, args[2]);
                                }
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid World"));
                        }
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid GUI Name"));
                    }
                }

                if (args[0].equalsIgnoreCase("boss")) {
                    if (args.length >= 6 && Util.isInt(args[2])) {
                        if (Main.worlds.contains(args[1].toLowerCase())) {
                            Main.bar.createBar(Integer.parseInt(args[2]), args[3], args[4], Util.stringJoin(args, 5));
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                if (p.getWorld().getName().equalsIgnoreCase(args[1])) {
                                    Main.bar.addPlayer(p);
                                }
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid World"));
                        }
                    } else {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-world-boss-usage")));
                    }
                }

                if (!(args[0].equalsIgnoreCase("chat") || args[0].equalsIgnoreCase("title") || args[0].equalsIgnoreCase("bar") || args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("gui") || args[0].equalsIgnoreCase("boss"))) {
                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-world-usage")).replace("{cmd}", label));
                }

            }

        }

        return false;
    }

}
