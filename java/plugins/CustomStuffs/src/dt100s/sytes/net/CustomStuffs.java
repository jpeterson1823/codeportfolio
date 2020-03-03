package dt100s.sytes.net;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomStuffs extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this,this);
        this.getCommand("wp").setExecutor(new Teleport());
        this.getCommand("smite").setExecutor(new Smite());
        this.getCommand("cobble").setExecutor(new CobbleStore());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome!\n" + ChatColor.WHITE + "You can now use " + ChatColor.GOLD + "waypoints" + ChatColor.RESET + "! Type /wp help for more info.");
        if (player.getName().equals("DolphinFTW")) {
            player.setDisplayName(ChatColor.DARK_GRAY + "-[" + ChatColor.BOLD + "" + ChatColor.RED + "INDEBTED" + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "]- " + ChatColor.RESET + "DolphinFTW");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equals("DolphinFTW"))
                    p.sendMessage(ChatColor.BOLD + "I think you may owe someone some money...");
                else p.sendMessage(ChatColor.RED + "DolphinFTW may owe someone some money...");
            }
        }
        else if (player.getName().equals("TheRealInvisa"))
            player.setDisplayName("-={ " + ChatColor.GOLD + "Owner" + ChatColor.RESET + " }=- " + ChatColor.RED + "TheRealInvisa" + ChatColor.RESET);
        else if (player.getName().equals("Tazax"))
            player.setDisplayName(ChatColor.BOLD + "|" + ChatColor.LIGHT_PURPLE + "MOB BOSS" + ChatColor.WHITE + "|" + ChatColor.RESET + "Tazax");
        else if (player.getName().equals("LegendaryTrex"))
            player.setDisplayName(ChatColor.RED + "{ " + ChatColor.GRAY + "Ghost" + ChatColor.RED + " }" + ChatColor.DARK_RED + " LegendaryTrex" + ChatColor.RESET);
    }

    @Override
    public void onDisable() {

    }
}
