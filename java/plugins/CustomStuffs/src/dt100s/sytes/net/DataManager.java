package dt100s.sytes.net;

import org.bukkit.*;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataManager {
    private final String PATH = "./tpdata/";
    private List<String> fileData = new ArrayList<>();
    private HashMap<String, Location> playerData = new HashMap<>();
    private Location loc;
    private File f;
    private boolean atMax = false;

    private Player player;

    public DataManager(Player player) {
        // Create player data file if non-existent as well as needed directories.
        this.player = player;
        try {
            new File(PATH.substring(0,PATH.length()-1)).mkdirs();
            f = new File(PATH+player.getName()+".txt");
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Could not create user file. All new waypoints will be lost on re-log. Contact plugin author for support.");
        }

        // Open and read all of the player data file and log each line to a List.
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {
                fileData.add(line);
                count++;
            }
            if (count >= 3) atMax = true;
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Failed to get file data.");
        }

        // Get location data from file data and add to a HashMap<String, Location>.
        for (String line : fileData) {
            String name = line.split(":")[0];
            String locData[] = line.split(":")[1].split(",");

            loc = new Location(Bukkit.getWorld(locData[0]), Double.valueOf(locData[1]), Double.valueOf(locData[2]), Double.valueOf(locData[3]));
            playerData.put(name,loc);
        }
    }

    // Searches through the HashMap<String, Location> to grab coords of saved waypoint.
    public Location getLoc(String name) {
        try {
            return playerData.get(name);
        }catch(Exception e){return null;}
    }

    // Adds waypoint to HashMap<String, Location>, then writes the necessary data to the player data file.
    public void addPlace(String name) {
        if (playerData.size() < 3 && !atMax) {
            playerData.put(name, player.getLocation());
            try {
                Location playerloc = player.getLocation();
                BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+player.getName()+".txt", true));
                writer.write(name+":"+player.getWorld().getName()+","+playerloc.getX()+","+playerloc.getY()+","+playerloc.getZ()+"\n");
                writer.close();
            }catch (Exception e){e.printStackTrace();}
            player.sendMessage("Added \""+name+"\" to list.");
        }
        else player.sendMessage(ChatColor.RED+"Cannot add location, you have hit the maximum of 3 waypoints!");
    }

    // Removes waypoint from HashMap<String, Location>, then writes the changes to the player data file.
    public void removePlace(String name) {
        playerData.remove(name);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH+player.getName()+".txt"));
            for (String key : playerData.keySet()) {
                writer.write(key+":"+playerData.get(key).getWorld().getName()+","+playerData.get(key).getX()+","+playerData.get(key).getY()+","+playerData.get(key).getZ()+"\n");
            }
            writer.close();
            player.sendMessage("Removed \""+name+"\" from list.");
        }catch (Exception e){e.printStackTrace();}
    }

    // Grabs all the names of saved waypoints.
    public String getList() {
        if (playerData.size() > 0) {
            String list = "";
            for (String key : playerData.keySet()) {
                list += key + ", ";
            }

            if (list.length() != 0) list = list.substring(0, list.length()-2);
            else list = "None";
            return list;
        }
        else return "You have no saved waypoints. Use /wp add <name> to add one.";
    }
}
