#include <stdio.h>
#include <cs50.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, string argv[])
{
    if (argc != 2)
    {
        printf("Usage: ./caesar [key]");
        return 1;
    }
    else
    {
        int k = atoi(argv[1]);
        string p = get_string("plaintext: ");

        printf("ciphertext: ");
        //ci=(pi+k)mod26
        for (int i = 0; i < strlen(p); i++)
        {
            if (isupper(p[i]))
            {
                printf("%c", ((p[i] + k) - 'A') % 26 + 'A');
            }
            else if (islower(p[i]))
            {
                printf("%c", ((p[i] + k) - 'a') % 26 + 'a');
            }
            else
            {
                printf("%c", p[i]);
            }
        }

        printf("\n");
    }
}