package Dev.ScalerGames.BroadcastPlus.Completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class BroadcastTAB implements TabCompleter {

    List<String> one = new ArrayList<String>();
    List<String> colors = new ArrayList<String>();
    List<String> styles = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if (one.isEmpty())  {
            one.add("chat"); one.add("title");
            one.add("bar"); one.add("gui");
            one.add("boss");
        }

        if (colors.isEmpty()) {
            colors.add("BLUE"); colors.add("GREEN");
            colors.add("PINK"); colors.add("PURPLE");
            colors.add("RED"); colors.add("WHITE");
            colors.add("YELLOW");
        }

        if (styles.isEmpty()) {
            styles.add("SOLID"); styles.add("SEGMENTED_6");
            styles.add("SEGMENTED_10"); styles.add("SEGMENTED_12");
            styles.add("SEGMENTED_20");
        }

        List<String> result = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : one) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        List<String> cResult = new ArrayList<String>();
        if (args[0].equalsIgnoreCase("boss")) {
            if (label.equalsIgnoreCase("localbroadcast") || label.equalsIgnoreCase("lb")) {
                if (args.length == 4) {
                    for (String a : colors) {
                        if (a.toLowerCase().startsWith(args[3].toLowerCase()))
                            cResult.add(a);
                    }
                    return cResult;
                }
            } else {
                if (args.length == 3) {
                    for (String a : colors) {
                        if (a.toLowerCase().startsWith(args[2].toLowerCase()))
                            cResult.add(a);
                    }
                    return cResult;
                }
            }
        }
        List<String> sResult = new ArrayList<String>();
        if (args[0].equalsIgnoreCase("boss")) {
            if (label.equalsIgnoreCase("localbroadcast") || label.equalsIgnoreCase("lb")) {
                if (args.length == 5) {
                    for (String a : styles) {
                        if (a.toLowerCase().startsWith(args[4].toLowerCase()))
                            sResult.add(a);
                    }
                    return sResult;
                }
            } else {
                if (args.length == 4) {
                    for (String a : styles) {
                        if (a.toLowerCase().startsWith(args[3].toLowerCase()))
                            sResult.add(a);
                    }
                    return sResult;
                }
            }
        }

        return null;
    }

}
