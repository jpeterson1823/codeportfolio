package dt100s.sytes.net;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Teleport implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            DataManager manager = new DataManager(player);

            if (args.length == 1) {
                Location loc = manager.getLoc(args[0]);
                if (args[0].equals("list")) {
                    player.sendMessage("Locations: "+manager.getList());
                }
                else if (args[0].equals("help")) {
                    player.sendMessage("/wp <add, remove, list, help> [waypoint_name]\n" +
                            ChatColor.GOLD +
                            "add:" +
                            ChatColor.GOLD +
                            "       Add new waypoint to list\n" +
                            "               required argument: waypoint name\n" +
                            ChatColor.GREEN +
                            "remove:" +
                            ChatColor.GOLD +
                            "    Remove saved waypoint from list.\n" +
                            "               required argument: waypoint name\n" +
                            ChatColor.GREEN +
                            "list:" +
                            ChatColor.GOLD +
                            "      List all currently saved waypoints." +
                            "               required argument: none\n" +
                            ChatColor.GREEN +
                            "help:" +
                            ChatColor.GOLD +
                            "      This menu. Pretty sure you've figured that one out by now." +
                            "               required argument: i think you know.");
                }
                else if (loc.getY() != -1.0) {
                    player.teleport(new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
                    player.sendMessage("Teleporting to \""+args[0]+"\"...");
                }
                else player.sendMessage(ChatColor.RED+"Unknown argument. Use /ctp help for more.");
            }
            else if (args.length == 2) {
                if (args[0].equals("add")) {
                    manager.addPlace(args[1]);
                }
                else if (args[0].equals("remove")) {
                    manager.removePlace(args[1]);
                    player.sendMessage("Removed location \""+args[1]+"\".");
                }
                else player.sendMessage(ChatColor.RED+"Unknown argument. Use /ctp help for more.");
            }
            else player.sendMessage(ChatColor.RED+"Incorrect number of arguments. Use /ctp help for more.");
        }
        else sender.sendMessage(ChatColor.RED+"Sender must be a player!");
        return true;
    }
}
