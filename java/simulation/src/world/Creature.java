package world;
import tools.Location;

public class Creature
{
    private boolean survive = false;
    private boolean reproduce = false;
    private boolean dead = false;

    private Location loc;

    public Creature()
    {
        loc = new Location(-1,-1);
    }

    public void forage(Food e[][])
    {
        reset();
        loc.x = (int)(Math.random()*e.length);
        loc.y = (int)(Math.random()*e.length);
        this.getFood(e);
    }

    private void getFood(Food e[][])
    {
        int foodVal = e[loc.x][loc.y].getValue();
        if (foodVal == 0)
        {
            dead = true;
        }
        else if (foodVal == 1)
        {
            survive = true;
            reproduce = false;
        }
        else
        {
            survive = true;
            reproduce = true;
        }
        e[loc.x][loc.y].setValue(0);
    }

    private void reset()
    {
        survive = false;
        reproduce = false;
    }

    public Location getLoc()
    {
        return loc;
    }

    public boolean canReproduce()
    {
        return reproduce;
    }

    public boolean isDead()
    {
        return dead;
    }
}
