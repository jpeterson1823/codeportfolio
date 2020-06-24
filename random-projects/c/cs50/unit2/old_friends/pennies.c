#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(int argc, string argv[])
{
    if (argc != 2)
    {
        printf("Usage: ./pennies.c days_in_month pennies_per_day");
        return 1;
    }

    //Get days in month
    int days = atoi(argv[1]);

    if (days < 28 || days > 31)
    {
        printf("Days per month must be at least 28 and cannot exceed 31.");
        return 1;
    }

    //Get pennies per day
    long pennies = atol(argv[2]);

    if (pennies < 1)
    {
        printf("Pennies per day must be at least 1.");
        return 1;
    }

    //calculate total dollar amount and print it.
    long long total = 0;
    for (int i = 0; i < days; i++)
    {
        total += pennies;
        pennies *= 2;
    }

    double money = (double)total / 100;
    printf("$%.2f\n", money);
}