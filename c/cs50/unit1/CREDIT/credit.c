#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <cs50.h>
int main(void)
{
    //adding mandatory comments
    long long cardNum = get_long("Card Number: ");
    long long temp = cardNum;
    int cardNumLen = 0;
    while (temp > 0)
    {
        temp /= 10;
        cardNumLen++;
    }

    //im pretty sure you know what is goin on in here.
    string type;
    if (cardNumLen == 13)
    {
        type = "VISA";
    }
    else if (cardNumLen == 15)
    {
        int secondDigit = (cardNum / 10000000000000) % 10;
        if (secondDigit == 4 || secondDigit == 7)
        {
            type = "AMEX";
        }
        else
        {
            printf("INVALID\n");
            return 0;
        }
    }
    //i need some comments
    else if (cardNumLen == 16)
    {
        int firstdigit = cardNum / 1000000000000000;
        int secondDigit = (cardNum / 100000000000000) % 10;
        if (firstdigit == 4)
        {
            type = "VISA";
        }
        else if (secondDigit == 1 || secondDigit == 2 || secondDigit == 3 || secondDigit == 4 || secondDigit == 5)
        {
            type = "MASTERCARD";
        }
        else
        {
            printf("INVALID\n");
            return 0;
        }
    }
    //comments comments
    else
    {
        printf("INVALID\n");
        return 0;
    }

    int sum = 0;
    //get the sum
    for (int i = 1; i <= cardNumLen; i++)
    {
        if (i % 2 != 0)
        {
            sum += cardNum % 10;
            cardNum /= 10;
        }
        else
        {
            int product = (cardNum % 10) * 2;
            if (product >= 10)
            {
                sum += product % 10;
                product /= 10;
            }
            sum += product;
            cardNum /= 10;
        }
    }
    //print thing if found correct/valid
    if (sum % 10 == 0)
    {
        printf("%s\n", type);
    }
    else
    {
        printf("INVALID\n");
    }
}