#include <stdio.h>
#include <cs50.h>

int main(void)
{
    int quarter = 25;
    int dime = 10;
    int nickel = 5;
    int penny = 1;

    int change = 0;

    //Makes sure that a positive value is given
    while (change <= 0)
    {
        change = (get_float("Change owed: ")  * 100+.5);
    }

    int coins = 0;

    //Cycle through the possible options
    while (change > 0)
    {
        if (change >= quarter)
        {
            change -= quarter;
            coins++;
        }
        else if (change >= dime)
        {
            change -= dime;
            coins++;
        }
        else if (change >= nickel)
        {
            change -= nickel;
            coins++;
        }
        else if (change >= penny)
        {
            change -= penny;
            coins++;
        }
    }

    printf("%d\n",coins);
}