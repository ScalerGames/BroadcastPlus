package Dev.ScalerGames.BroadcastPlus.Completers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class BroadcastWorldTab implements TabCompleter {

    List<String> one = new ArrayList<String>();
    List<String> two = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        //Arg-1
        if (one.isEmpty())  {

            one.add("chat"); one.add("title");
            one.add("bar"); one.add("help");
            one.add("gui");

        }

        //Arg-2
        if (two.isEmpty()) {

            List<World> worlds = Bukkit.getWorlds();
            for (World i : worlds) {

                two.add(i.getName());

            }

        }


        List<String> result = new ArrayList<String>();
        List<String> resulttwo = new ArrayList<String>();
        if (args.length == 1) {
            for (String a : one) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if (args.length == 2) {
            for (String b : two) {
                if (b.toLowerCase().startsWith(args[1].toLowerCase()))
                    resulttwo.add(b);
            }
            return resulttwo;
        }

        return null;
    }

}
