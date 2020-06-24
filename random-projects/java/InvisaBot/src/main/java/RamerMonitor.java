import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

public class RamerMonitor implements Runnable{

    private Member mahBoi = null;
    private boolean ramerOnline = false;
    private JDA jda;
    private Thread ramerInfoThread;
    private boolean loop = true;

    public RamerMonitor(JDA jda) {
        this.jda = jda;
        Guild testGuild = jda.getGuildById("718243029848424460");
        List<Member> members = testGuild.getMembers();

        for (Member member : members) {
            System.out.println(member.getUser().getName());
        }
    }

    private boolean getRamerOnline() {
        return ramerOnline;
    }

    public void run() {
        System.out.println("ramerInfoThread has started!");
        while (!endLoop()) {
            Guild testGuild = jda.getGuildById("718243029848424460");
            List<Member> members = testGuild.getMembers();

            for (Member member : members) {
                if (member.getUser().getName().equals("InvisaBot")) {
                    if (member.getOnlineStatus() == OnlineStatus.ONLINE) {
                        mahBoi = member;
                    }
                }
            }

            if (mahBoi != null) {
                testGuild.getDefaultChannel().sendMessage("**----- ATTENTION!**\nSergeant Ramer is online. Show some respect, maggots!\n\n**HAND, SALUTE!**");
                loop = false;
                System.out.println("RAMER IS ONLINE");
            }
        }
        System.out.println("ramerInfoThread finished...");
    }

    public void start() {
        System.out.println("Starting ramerInfoThread...");
        if (ramerInfoThread == null) {
            ramerInfoThread = new Thread(this,"ramerInfoThread");
            ramerInfoThread.start();
        }
    }

    private boolean endLoop() {
        return !loop;
    }
}
