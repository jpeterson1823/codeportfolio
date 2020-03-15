#include <stdio.h>
#include <stdlib.h>
#include <cs50.h>
#include <ctype.h>
#include <string.h>
//32

int main(int argc, string argv[])
{
    //check if used right
    if (argc != 2)
    {
        printf("Usage: ./vigenere <key>\n");
        return 1;
    }

    string salt = argv[1];
    //check for numbers in keyword
    for (int i = 0; i < strlen(salt); i++)
    {
        if (isdigit(salt[i]))
        {
            printf("No numbers in the keyword!\n");
            return 1;
        }
    }

    //Turn the input string into managable char array
    string text = get_string("plaintext: ");


    //getting number values for letters
    int numKey[strlen(salt)];
    for (int i = 0; i < strlen(salt); i++)
    {
        if (isupper(salt[i]))
        {
            numKey[i] = salt[i] - 65;
        }
        else
        {
            numKey[i] = salt[i] - 97;
        }
    }

    //creating ciphertext
    int numCount = 0;
    string otherChars = " !\"#$%&'()*+`-./?,";
    for (int i = 0; i < strlen(text); i++)
    {
        if (numCount >= strlen(salt))
        {
            numCount = 0;
        }
        //check for uppercase
        if (isupper(text[i]))
        {
            text[i] = (text[i] + numKey[numCount] - 'A') % 26 + 'A';
        }
        //check for special characters
        else if (strrchr(otherChars, text[i]) != NULL)
        {
            text[i] = text[i];
            numCount--;
        }
        //lowercase letters
        else
        {
            text[i] = (text[i] + numKey[numCount] - 'a') % 26 + 'a';
        }
        numCount++;
    }

    //print ciphertext, go figure
    printf("ciphertext: %s\n", text);
}