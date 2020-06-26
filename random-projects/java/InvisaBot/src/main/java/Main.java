import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;

import java.nio.channels.Channel;
import java.util.List;

public class Main {

    public static void main(String args[]) {

        String token = "cannot upload this, get from site when testing...";
        JDA jda = null;

        try {
            jda = JDABuilder.createDefault(token).build();
            jda.awaitReady();
            System.out.println("Bot successfully started!");
        } catch (Exception e) {
            System.out.println("Error encountered during startup. Starting error log then exiting.");
            new  Logger().log(e.toString());
            System.exit(-1);
        }

        RamerMonitor ramerMonitor = new RamerMonitor(jda);
        ramerMonitor.start();

    }

}
