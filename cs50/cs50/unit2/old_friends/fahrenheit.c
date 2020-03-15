#include <stdio.h>
#include <cs50.h>

int main(int argc, string argv[])
{

    //checks to see if the correct amount of arguments are added.
    if (argc != 2)
    {
        printf("Usage: ./fahrenheit temp_in_celcius");
        return 1;
    }

    //This turns celcius into fahrenheit
    float celcius = atof(argv[1]);
    float fahrenheit = ((celcius * 9) / 5) + 32;
    printf("%.1f\n", fahrenheit);
}