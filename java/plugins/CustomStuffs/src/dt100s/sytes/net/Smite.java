package dt100s.sytes.net;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class Smite implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String list, String[] args) {
        if (args.length == 1){
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equals(args[0])) {
                    p.getWorld().strikeLightningEffect(p.getLocation());
                    p.setHealth(0.0);
                    Bukkit.broadcast(ChatColor.BLUE+p.getName()+" was struck down by God.","@a");
                    break;
                }
            }
        }
        return true;
    }

    private void smitePlayer(Player player) {
        player.getLocation().getWorld().strikeLightningEffect(player.getLocation());
        player.setHealth(0.0);
        Bukkit.getServer().broadcastMessage(player.getDisplayName()+ChatColor.RESET+""+ChatColor.BLUE+" was struck down by the "+ChatColor.GOLD+"Gods"+ChatColor.BLUE+".");
    }
}
