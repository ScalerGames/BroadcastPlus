package Dev.ScalerGames.BroadcastPlus.Utils;

import Dev.ScalerGames.BroadcastPlus.Main;
import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

public class Util {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void logger(String log) {
        Main.getInstance().getLogger().info(Format.color(log));
    }

    public static String stringJoin(String[] args, Integer from) {
        return StringUtils.join(Arrays.copyOfRange(args, from, args.length), " ");
    }


}
