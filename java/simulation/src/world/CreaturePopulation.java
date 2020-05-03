package world;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class CreaturePopulation {
    public ArrayList<Creature> creatures = new ArrayList<>();


    public CreaturePopulation(int initPop)
    {
        for (int i = 0; i < initPop; i++)
        {
            creatures.add(new Creature());
        }
    }

    public void mainLoop(Food e[][])
    {
        for (Creature creature : creatures)
        {
            creature.forage(e);
        }
        logLoc();
        cleanPop();
        reproduce();
        //System.out.println("Pop loop, "+creatures.size());
    }

    public void cleanPop()
    {
        ListIterator<Creature> it = creatures.listIterator();
        while (it.hasNext())
        {
            if (it.next().isDead()) it.remove();
        }
    }

    public void reproduce()
    {
        ListIterator<Creature> it = creatures.listIterator();
        while (it.hasNext())
        {
              if (it.next().canReproduce()) it.add(new Creature());
        }
    }

    public int getPop()
    {
        return creatures.size();
    }

    private void logLoc()
    {
        File file = new File("./locLog.txt");
        BufferedWriter log;
        try
        {
            if (file.exists()) log = new BufferedWriter(new FileWriter(file,true));
            else log = new BufferedWriter(new FileWriter(file));

            log.write("Population: "+getPop()+"\n");

            int count = 1;
            for (Creature creature : creatures)
            {
                log.write("Creature "+(count)+": ("+creature.getLoc().x+", "+creature.getLoc().y+")\n");
                count++;
            }
            log.write("\n");
            log.close();
        }
        catch(IOException e){e.printStackTrace();}
    }
}
