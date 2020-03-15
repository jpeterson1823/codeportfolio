#include <stdio.h>
#include <cs50.h>

int main(void)
{
    //Getting input.
    int isbn = get_long("ISBN: ");

    int arr[10];

    for (int i = 0; i < 10; i++)
    {
        int temp = isbn % 10;
        arr[i] = temp;
        isbn /= 10;
    }

    int isbnTotal = 0;
    int count = 1;

    for (int i = 9; i > 0; i--)
    {
        isbnTotal += count * arr[i];
        count++;
    }

    if (isbnTotal % 11 == arr[0])
    {
        printf("YES\n");
    }
    else
    {
        printf("NO\n");
    }
}