package dt100s.sytes.net;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Teleport implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            LevelManager levelManager = new LevelManager(player);
            TPManager wpManager = new TPManager(player, levelManager.getLevel(player));

            if (args.length == 1) {
                Location loc = wpManager.getLoc(args[0]);
                if (args[0].equals("list")) {
                    player.sendMessage("Locations: "+wpManager.getList());
                }
                else if (args[0].equals("level")) player.sendMessage("Current Level: "+levelManager.getLevel(player));
                else if (args[0].equals("levelup")) {
                    levelManager.levelUp(player);
                    wpManager.setWplevel(wpManager.getWplevel()+1);
                }
                else if (args[0].equals("help")) {
                    player.sendMessage("/wp <add, remove, list, help> [waypoint_name]\n" +
                            ChatColor.GOLD +
                            "add:" +
                            ChatColor.GOLD +
                            " Add new waypoint to list\n" +
                            ChatColor.GREEN +
                            "remove:" +
                            ChatColor.GOLD +
                            " Remove saved waypoint from list.\n" +
                            ChatColor.GREEN +
                            "list:" +
                            ChatColor.GOLD +
                            "List all currently saved waypoints." +
                            ChatColor.GREEN +
                            "level:" +
                            ChatColor.GOLD +
                            "Show current WPLevel." +
                            ChatColor.GREEN +
                            "levelup:" +
                            ChatColor.GOLD +
                            "Level up WPLevel." +
                            ChatColor.GREEN +
                            "help:" +
                            ChatColor.GOLD +
                            "      This menu.");
                }
                else if (loc == null) player.sendMessage("Location is not in your list!");
                else if (loc.getY() != -1.0) {
                    if (player.getWorld().getName() == loc.getWorld().getName()) {
                        player.teleport(new Location(player.getWorld(), loc.getX(), loc.getY(), loc.getZ()));
                        player.sendMessage("Teleporting to \""+args[0]+"\"...");
                    }
                    else {
                        player.sendMessage(ChatColor.RED+"You must be in the same dimension as your waypoint to teleport!");
                    }
                }
                else player.sendMessage(ChatColor.RED+"Unknown argument. Use /wp help for more.");
            }
            else if (args.length == 2) {
                if (args[0].equals("add")) {
                    wpManager.addPlace(args[1]);
                }
                else if (args[0].equals("remove")) {
                    wpManager.removePlace(args[1]);
                }
                else player.sendMessage(ChatColor.RED+"Unknown argument. Use /wp help for more.");
            }
            else player.sendMessage(ChatColor.RED+"Incorrect number of arguments. Use /wp help for more.");
        }
        else sender.sendMessage(ChatColor.RED+"Sender must be a player!");
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("wp") && sender instanceof Player) {
            if (args.length == 1) {
                ArrayList<String> list = new ArrayList<>();
                list.add("add");
                list.add("remove");
                list.add("list");
                list.add("level");
                list.add("levelup");
                return list;
            }
            else if (args.length == 2 && args[0].equals("remove")) {
                return new TPManager((Player)sender, new LevelManager((Player)sender).getLevel((Player)sender)).getLocationNames();
            }
            else return new ArrayList<>();
        }
        return null;
    }
}
