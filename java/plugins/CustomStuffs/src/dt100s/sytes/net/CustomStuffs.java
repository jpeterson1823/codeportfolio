package dt100s.sytes.net;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomStuffs extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this,this);
        this.getCommand("wp").setExecutor(new Teleport());
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.LIGHT_PURPLE + "Welcome! You can now use waypoints! Type /wp help for more info.");
        if (player.getName().equals("DolphinFTW")) {
            player.setDisplayName(ChatColor.DARK_GRAY+"-["+ChatColor.BOLD+""+ChatColor.RED+"INDEBTED"+ChatColor.RESET+""+ChatColor.DARK_GRAY+"]- "+ChatColor.WHITE+"DolphinFTW");
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equals("DolphinFTW")) p.sendMessage(ChatColor.BOLD+"I think you may owe someone some money...");
                else p.sendMessage(ChatColor.BLUE+"DolphinFTW may owe someone some money...");
            }
        }
        else if (player.getName().equals("TheRealInvisa")) player.setDisplayName("-={ "+ChatColor.GOLD+""+ChatColor.BOLD+"Owner"+ChatColor.RESET+" }=- "+ChatColor.BLUE+""+ChatColor.ITALIC+"TheRealInvisa");
    }

    @Override
    public void onDisable() {

    }
}
