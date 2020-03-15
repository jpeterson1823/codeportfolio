#include <stdio.h>
#include <cs50.h>
#include <math.h>

int main(void)
{
    //Get days in month
    int days;
    do
    {
        days = get_int("Days in month: ");
    }
    while (days > 31 || days < 28);

    //Get pennies per day
    long pennies = -1;
    do
    {
        pennies = get_long("Pennies on first day: ");
    }
    while (pennies < 1);


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