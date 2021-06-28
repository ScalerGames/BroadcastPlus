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

import java.util.ArrayList;
import java.util.List;

public class Broadcast implements CommandExecutor {

    public static List<String> presets = new ArrayList<String>();

    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("broadcast") || label.equalsIgnoreCase("announce")) {

            if (s.hasPermission("bp.broadcast")) {

                if (args.length == 0) {
                    s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-usage")));
                }

                if (args.length >= 1) {

                    String prefix = Main.getInstance().getConfig().getString("BroadcastPrefix");

                    if (args[0].equalsIgnoreCase("chat")) {
                        if (presets.contains(args[1])) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                for (String msg : Main.getInstance().getConfig().getStringList("Presets." + args[1] + ".message")) {
                                    p.sendMessage(Format.placeholder(p, msg));
                                }
                            }
                        } else {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.sendMessage(Format.color(prefix + "&r ") + Format.placeholder(p, Util.stringJoin(args, 1)));
                            }
                        }
                    }

                    if (args[0].equalsIgnoreCase("bar")) {
                        if (args.length >= 2) {
                            if (Util.isInt(args[1])){
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    ActionBar.sendActionBar(p, Util.stringJoin(args, 2), Integer.parseInt(args[1]));
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Timing"));
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-bar-usage")));
                        }
                    }

                    if (args[0].equalsIgnoreCase("title")) {
                        if (Main.getInstance().getConfig().getConfigurationSection("Presets").getKeys(false).contains(args[1])) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                Title.sendTitle(Main.getInstance().getConfig().getString("Presets."), p);
                            }
                        }
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            Title.sendTitle(Util.stringJoin(args, 1), p);
                        }
                    }

                    if (args[0].equalsIgnoreCase("gui")) {
                        if (args.length == 2) {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                GuiCreator.generate(p, args[1]);
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid GUI Name"));
                        }
                    }

                    if (args[0].equalsIgnoreCase("boss")) {
                        if (args.length >= 5) {
                            if (Util.isInt(args[1])) {
                                Main.bar.createBar(Integer.parseInt(args[1]), args[2], args[3], Util.stringJoin(args, 4));
                                for (Player p : Bukkit.getOnlinePlayers()) {
                                    Main.bar.addPlayer(p);
                                }
                            } else {
                                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + "&cInvalid Timing"));
                            }
                        } else {
                            s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-boss-usage")));
                        }
                    }

                    if (!(args[0].equalsIgnoreCase("chat") || args[0].equalsIgnoreCase("title") || args[0].equalsIgnoreCase("bar") || args[0].equalsIgnoreCase("gui") || args[0].equalsIgnoreCase("boss"))) {
                        s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-options")));
                    }

                }

            } else {
                s.sendMessage(Format.color(Lang.getLangConfig().getString("prefix") + Lang.getLangConfig().getString("broadcast-permission")));
            }

        }

        return false;
    }

}
