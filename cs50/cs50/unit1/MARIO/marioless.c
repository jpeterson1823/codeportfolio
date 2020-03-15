#include <stdio.h>
#include <cs50.h>

int main(void)
{
    int height = 9;

    while (height > 8 || height < 1)
    {
        height = get_int("Height: ");
    }
    //get height of stairs

    int spaces = height - 1;
    int blocks = 1;

    //print stairs
    for (int i = height; i > 0; i--)
    {
        //print spaces
        for (int j = spaces; j > 0; j--)
        {
            printf(" ");
        }
        //print blocks
        for (int j = blocks; j > 0; j--)
        {
            printf("#");
        }
        blocks += 1;
        spaces -= 1;
        printf("\n");
    }
}