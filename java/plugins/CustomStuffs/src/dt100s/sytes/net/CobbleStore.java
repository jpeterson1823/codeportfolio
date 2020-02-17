package dt100s.sytes.net;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CobbleStore implements CommandExecutor, TabCompleter {
    private Player player;
    private int cobble = -1;

    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        if (sender instanceof Player) {
            this.player = (Player)sender;

            // Info File
            File info = new File("./cobbledata/"+player.getName()+".txt");

            // Check for existence of user file. If file not found, create it.
            if (!info.exists()) {
                try {
                    info.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    player.sendMessage("Could not load cobblestone storage file. Please contact server administrator for assistance.");
                }
            }

            // Read file and store data.
            try {
                BufferedReader reader = new BufferedReader(new FileReader(info));
                String fileLine;
                if((fileLine = reader.readLine()) != null) cobble = Integer.parseInt(fileLine);
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
            }

            if (args[0] == "store") {
                player.sendMessage("Cobblestone Stored: "+this.store());
            }
        }
        else
            sender.sendMessage(ChatColor.RED+"Command user must be a player!");

        return true;
    }

    public int getCobble() {
        return this.cobble;
    }

    public int store() {
        int cobbleOnPlayer = 0;
        PlayerInventory inventory = player.getInventory();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null) {
                player.sendMessage(item.getType().name());
                if(item.getType().name().equals("COBBLESTONE")) {
                    cobbleOnPlayer += item.getAmount();
                    item.setAmount(0);
                }
            }
        }
        return cobbleOnPlayer;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
