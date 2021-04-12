package Dev.ScalerGames.BroadcastPlus.Completers;

import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.GroupBroadcast;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GroupBroadcastTab implements TabCompleter {

    List<String> one = new ArrayList<String>();
    List<String> two = new ArrayList<String>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if (one.isEmpty()) {

            one.add("chat"); one.add("title");
            one.add("bar"); one.add("gui");

        }

        if (two.isEmpty()) {

            if (GroupBroadcast.lpEnabled()) {

                Set<Group> groupList = LuckPermsProvider.get().getGroupManager().getLoadedGroups();

                for (Group g : groupList) {
                    two.add(g.getName());
                }

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
