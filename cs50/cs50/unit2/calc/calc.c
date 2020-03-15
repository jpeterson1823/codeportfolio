#include <stdio.h>
#include <stdlib.h>
#include <cs50.h>

//declairing dmod method
double dmod(double x, double y);

int main(int argc, string argv[])
{
    //creating check bools
    bool int1 = true;
    bool int2 = true;
    bool double1 = false;
    bool double2 = false;

    if (argc != 4)
    {
        printf("Usage: ./calc.c number operand number");
        return 1;
    }



    float result = 0;
    float num1 = atof(argv[1]);
    float num2 = atof(argv[3]);

    //checking each possible case
    switch (argv[2][0])
    {
        case '+':
            result = (double)num1 + (double)num2;
            break;
        case '-':
            result = (double)num1 - (double)num2;
            break;
        case '*':
        case 'x':
            result = (double)num1 * (double)num2;
            break;
        case '/':
            result = (double)num1 / (double)num2;
            break;
        case '%':
            result = dmod(num1, num2);
            break;
    }
    if (!result)
    {
        return 1;
    }
    printf("%.6f\n", result);
}


//Mod for doubles, making things easier
double dmod(double x, double y)
{
    return x - (int)(x / y) * y;
}