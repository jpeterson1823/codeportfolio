package world;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Environment
{
    private final int FOOD2_RATE = 40;
    private final double FOOD_ABUNDANCE = 0.8;

    private static final int DIMENSION = 150;
    public static Food environment[][] = new Food[DIMENSION][DIMENSION];

    private int days = 0;

    public Environment()
    {
        this.initializeEnvironment();
        this.growFood();
        this.logEnvironmentData();
    }

    // Writes the current environment into a text file.
    private void logEnvironmentData()
    {
        try
        {
            BufferedWriter file = new BufferedWriter(new FileWriter("./environmentData.txt"));
            for (int i = 0; i < DIMENSION; i++)
            {
                for (int j = 0; j < DIMENSION; j++)
                {
                    file.write(Integer.toString(environment[i][j].getValue()));
                }
                file.write('\n');
            }
            file.close();
        }
        catch (IOException e){e.printStackTrace();}
    }

    // Initially sets all Food object values to 0
    private void initializeEnvironment()
    {
        try
        {
            for (int i = 0; i < DIMENSION; i++)
            {
                for (int j = 0; j < DIMENSION; j++)
                {
                    environment[i][j] = new Food(0);
                }
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    private void growFood()
    {
        try
        {
            int amountFood = (int)(DIMENSION*DIMENSION*FOOD_ABUNDANCE);

            int x = (int)(Math.random()*DIMENSION);
            int y = (int)(Math.random()*DIMENSION);

            for (int i = 0; i < amountFood; i++)
            {
                while (environment[x][y].getValue() != 0)
                {
                    x = (int)(Math.random()*DIMENSION);
                    y = (int)(Math.random()*DIMENSION);
                }

                if ((int)(Math.random()*100) >= FOOD2_RATE) environment[x][y].setValue(1);
                else environment[x][y].setValue(2);
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void regrowFood()
    {
        initializeEnvironment();
        growFood();
    }

    public void addDay()
    {
        days+=1;
    }
    public int getDays()
    {
        return days;
    }

    public String getEnvStr()
    {
        String temp = "";
        for (int i = 0; i < DIMENSION; i++)
        {
            for (int j = 0; j < DIMENSION; j++)
            {
                temp+=environment[i][j].getValue()+"   ";
            }
            temp = temp.substring(0,temp.length()-3)+"\n";
        }
        return temp.substring(0,temp.length()-1);
    }
}
