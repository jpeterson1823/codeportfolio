#include <stdio.h>
#include <cs50.h>

int main(void)
{
    //This turns celcius into fahrenheit
    float celcius = get_float("Enter your celcius temperature: ");
    float fahrenheit = ((celcius * 9) / 5) + 32;
    printf("%.1f\n", fahrenheit);
}