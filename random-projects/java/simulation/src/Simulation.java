import graphics.Window;
import world.CreaturePopulation;
import world.Environment;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Simulation
{
    private static boolean start = false;
    private static boolean endsim = false;

    public static void main(String args[])
    {
        Window window = new Window();
        Environment environment = new Environment();

        ActionListener listener = (e -> {
            if (e.getSource() == window.start && window.getInput() > -1) start=true;
            if (e.getSource() == window.end) endsim = true;
        });

        window.start.addActionListener(listener);
        window.end.addActionListener(listener);
        window.updateInfo(environment.getDays(),1,environment.getEnvStr());

        while(!canStart())
        {
            canStart();
            System.out.println("init loop");
        }

        CreaturePopulation pop = new CreaturePopulation(window.getInput());

        while(!endSim())
        {
            //System.out.println("LOOPING");
            window.updateInfo(environment.getDays(),pop.getPop(),environment.getEnvStr());
            environment.addDay();
            pop.mainLoop(environment.environment);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            environment.regrowFood();
        }

        window.dispose();
        System.out.println("Ended Simulation");
    }

    private static void logSimData(String data)
    {
        try {
            BufferedWriter graph = new BufferedWriter(new FileWriter("./graph.data "));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean canStart()
    {
        return start;
    }

    private static boolean endSim()
    {
        return endsim;
    }
}
