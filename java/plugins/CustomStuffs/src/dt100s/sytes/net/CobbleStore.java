package dt100s.sytes.net;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CobbleStore implements CommandExecutor, TabCompleter {
    private Player player;
    private int cobble = -1;

    private File info;

    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        if (sender instanceof Player) {
            if (args.length > 0) {
                if (args.length == 1) {
                    if (args[0].equals("store")) {
                        this.player = (Player)sender;

                        // Info File
                        this.info = new File("./cobbledata.txt");

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

                    else if (args[0].equals("get")) {
                        player.sendMessage("Not yet implemented.");
                    }
                }

                else if (args.length == 2 && args[0].equals("get")) {
                    int stacks;
                    try {
                        stacks = Integer.parseInt(args[1]);
                    }catch(Exception e){
                        player.sendMessage(ChatColor.RED+"You must provide a proper number of stacks.\nEx: /cobble get 2");
                        return true;
                    }

                    this.getCobble(stacks);
                    this.
                }
            }
            else player.sendMessage("Current stored cobblestone: "+this.cobble);
        }
        else
            sender.sendMessage(ChatColor.RED+"Command user must be a player!");

        return true;
    }

    private ArrayList<ItemStack> getCobble(int stacks) {
        ArrayList<ItemStack> cobbleStacks = new ArrayList<>();
        if (this.cobble >= stacks*64) {
            for (int i = 0; i < stacks; i++) {
                cobbleStacks.add(new ItemStack(Material.COBBLESTONE,64));
            }
            this.cobble -= stacks*64;
        }
        else {
            player.sendMessage("You do not have "+(stacks*64)+" cobblestone in the aether. Returning "+(this.cobble)+" cobblestone...");
            while (this.cobble > 0) {
                if (this.cobble >= 64) {
                    cobbleStacks.add(new ItemStack(Material.COBBLESTONE,64));
                    this.cobble -= 64;
                }
                else {
                    cobbleStacks.add(new ItemStack(Material.COBBLESTONE,this.cobble));
                    this.cobble = 0;
                }
            }
        }
        return cobbleStacks;
    }

    public int store() {
        if (cobble != -1) {
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

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(info));
            }catch(Exception e) {
                e.printStackTrace();
            }

            return cobbleOnPlayer;
        }
        else player.sendMessage("Could not load your cobblestone info, please contact the server administrator.");

        return -1;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
