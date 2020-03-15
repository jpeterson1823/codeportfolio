#include <stdio.h>
#include <cs50.h>
#include <crypt.h>
#include <string.h>

/*
anushree:50xcIMJ0y.RXo
brian:50mjprEcqC/ts
bjbrown:50GApilQSG3E2
lloyd:50n0AAUD.pL8g
malan:50CcfIk1QrPr6
maria:509nVI8B9VfuA
natmelo:50JIIyhDORqMU
rob:50JGnXUgaafgc
stelios:51u8F0dkeDSbY
zamyla:50cI2vYkF0YU2
*/

int main(int argc, string argv[])
{
    if (argc < 2)
    {
        printf("Check command args!\n");
        return 1;
    }

    const int totalLetters = 53; //full alphabet and a \0 at the end
    string possibleLetters = "\0abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    string hash = argv[1];

    char salt[3];
    memcpy(salt, hash, 2);
    salt[2] = '\0';

    char passTemplate[6] = "\0\0\0\0\0\0";

    for (int fifth = 0; fifth < totalLetters; fifth++)
    {
        for (int fourth = 0; fourth < totalLetters; fourth++)
        {
            for (int third = 0; third < totalLetters; third++)
            {
                for (int second = 0; second < totalLetters; second++)
                {
                    for (int first = 1; first < totalLetters; first++)
                    {
                        passTemplate[0] = possibleLetters[first];
                        passTemplate[1] = possibleLetters[second];
                        passTemplate[2] = possibleLetters[third];
                        passTemplate[3] = possibleLetters[fourth];
                        passTemplate[4] = possibleLetters[fifth];

                        if (strcmp(crypt(passTemplate, salt), hash) == 0)
                        {
                            printf("Cracked: %s\n", passTemplate);
                            return 0;
                        }
                    }
                }
            }
        }
    }
    printf("Could Not Crack.\n");
    return 2;
}