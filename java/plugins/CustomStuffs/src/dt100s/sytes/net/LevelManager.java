package dt100s.sytes.net;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;

public class LevelManager {
    private final String PATH = "./leveldata/";
    private File f;
    private ArrayList<String> fileData = new ArrayList<>();
    private int playerLevel;

    public LevelManager(Player player) {
        try {
            new File(PATH.substring(0,PATH.length()-1)).mkdirs();
            f = new File(PATH+"leveldata.txt");
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            player.sendMessage("Could not create level file. Levels will not be saved upon re-log.");
        }

        this.loadFileData(f);

        // Retrieve player level
        playerLevel = this.getLevel(player);
        if (playerLevel == -1) player.sendMessage(ChatColor.RED+"Player WPLevel could not be loaded, do not upgrade as it will not be saved. Contact plugin author.");
    }

    private void loadFileData(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.add(line);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(f));
            for (String data : fileData) {
                writer.write(data+"\n");
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Failed to save data");
            return false;
        }
        return true;
    }

    private int getLevel(Player player) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.split(":")[0].equals(player.getName())) {
                    reader.close();
                    return Integer.parseInt(line.split(":")[1]);
                }
            }

            // If user is not found in file, create instance and set level to 0 and add it to fileData.
            fileData.add(player.getName()+":0");
            this.save();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getLevel() {
        return playerLevel;
    }

    public void levelUp(Player player) {
        player.sendMessage("Current XP Level: "+player.getLevel());
        if (playerLevel == -1) player.sendMessage("Your WPLevel was not loaded. Contact your server admin for assistance.");

        if (playerLevel == 0 && player.getLevel() >= 30) {
            player.setLevel(player.getLevel() - 30);
            playerLevel++;
            player.sendMessage("Leveled Up! You are now level 1");
        }
        else if (playerLevel == 1 && player.getLevel() >= 50) {
            player.setLevel(player.getLevel() - 50);
            playerLevel++;
            player.sendMessage("Leveled Up! You are now level 2");
        }
        else if (playerLevel == 2 && player.getLevel() >= 70) {
            player.setLevel(player.getLevel() - 70);
            playerLevel++;
            player.sendMessage("Leveled Up! You are now level 3");
        }
        else if (playerLevel == 3){
            player.sendMessage(ChatColor.RED+"You are at the maximum level!");
        }
        else player.sendMessage("You don't have the required XP to level up.\nLevel 1: 30 XP\nLevel 2: 50 XP\nLevel 3: 70 XP");

        for (int i = 0; i < fileData.size(); i++) {
            if (fileData.get(i).split(":")[0].equals(player.getName())) {
                fileData.remove(i);
            }
        }
        fileData.add(player.getName()+":"+playerLevel);
        if(!this.save()) player.sendMessage(ChatColor.RED+"Could not save upgrade. Contact server admin for assistance.");
    }
}
