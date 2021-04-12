package Dev.ScalerGames.BroadcastPlus;

import Dev.ScalerGames.BroadcastPlus.Commands.BroadcastPlus;
import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.Broadcast;
import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.BroadcastWorld;
import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.GroupBroadcast;
import Dev.ScalerGames.BroadcastPlus.Commands.Broadcasting.LocalBroadcast;
import Dev.ScalerGames.BroadcastPlus.Completers.BroadcastPlusTab;
import Dev.ScalerGames.BroadcastPlus.Completers.BroadcastTAB;
import Dev.ScalerGames.BroadcastPlus.Completers.BroadcastWorldTab;
import Dev.ScalerGames.BroadcastPlus.Completers.GroupBroadcastTab;
import Dev.ScalerGames.BroadcastPlus.Files.Config;
import Dev.ScalerGames.BroadcastPlus.Files.Gui;
import Dev.ScalerGames.BroadcastPlus.Files.Lang;
import Dev.ScalerGames.BroadcastPlus.Methods.Gui.GuiCreator;
import Dev.ScalerGames.BroadcastPlus.Methods.Gui.GuiListener;
import Dev.ScalerGames.BroadcastPlus.Utils.Format;
import Dev.ScalerGames.BroadcastPlus.Utils.Util;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    public static List<String> worlds = new ArrayList<String>();

    @Override
    public void onEnable() {
        plugin = this;
        enableCommands();
        enableFiles();
        enablePlugins();
        enableListeners();
        for (World world : Bukkit.getWorlds()) {
            worlds.add(world.getName());
        }
        for (String menus : Gui.getGuiConfig().getConfigurationSection("Menus").getKeys(false)) {
            GuiCreator.list.add(Format.stripColor(Gui.getGuiConfig().getString("Menus." + menus.substring(menus.lastIndexOf(".") + 1) + ".name")));
        }
        for (String preset : getConfig().getConfigurationSection("Presets").getKeys(false)) {
            Broadcast.presets.add(getConfig().getConfigurationSection("Presets." + preset.substring(preset.lastIndexOf(".") + 1)).getName());
        }
        Util.logger("&3GUI List: " + GuiCreator.list.toString() + " Preset List: " + Broadcast.presets.toString());
    }

    @Override
    public void onDisable() {
        Util.logger("&cDisabled BroadcastPlus");
    }


    public static Main getInstance() {
        return plugin;
    }

    public void enableCommands() {
        getCommand("broadcast").setExecutor((CommandExecutor)new Broadcast());
        getCommand("broadcast").setTabCompleter((TabCompleter)new BroadcastTAB());
        getCommand("broadcastplus").setExecutor((CommandExecutor)new BroadcastPlus());
        getCommand("bp").setExecutor((CommandExecutor)new BroadcastPlus());
        getCommand("broadcastplus").setTabCompleter((TabCompleter)new BroadcastPlusTab());
        getCommand("bp").setTabCompleter((TabCompleter)new BroadcastPlusTab());
        getCommand("broadcastworld").setExecutor((CommandExecutor)new BroadcastWorld());
        getCommand("broadcastworld").setTabCompleter((TabCompleter)new BroadcastWorldTab());
        getCommand("bw").setExecutor((CommandExecutor)new BroadcastWorld());
        getCommand("localbroadcast").setExecutor((CommandExecutor)new LocalBroadcast());
        getCommand("lb").setExecutor((CommandExecutor)new LocalBroadcast());
        getCommand("localbroadcast").setTabCompleter((TabCompleter)new BroadcastTAB());
        getCommand("lb").setTabCompleter((TabCompleter)new BroadcastTAB());
        getCommand("groupbroadcast").setExecutor((CommandExecutor)new GroupBroadcast());
        getCommand("gb").setExecutor((CommandExecutor)new GroupBroadcast());
        getCommand("groupbroadcast").setTabCompleter((TabCompleter)new GroupBroadcastTab());
        getCommand("gb").setTabCompleter((TabCompleter)new GroupBroadcastTab());
    }

    public void enableFiles() {
        Config.enableConfig();
        Gui.saveDefaultGui();
        Lang.saveDefaultLang();
    }

    public void enablePlugins() {

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into PlaceholderAPI"));
        }
        if (Bukkit.getPluginManager().getPlugin("LuckPerms") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into LuckPerms"));
        }

        if  (Bukkit.getPluginManager().getPlugin("HeadDatabase") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
            getLogger().info(Format.color("&2Successfully hooked into HeadDatabase"));
        }

    }

    public void enableListeners() {
        Bukkit.getPluginManager().registerEvents(new GuiListener(), this);
    }

}
