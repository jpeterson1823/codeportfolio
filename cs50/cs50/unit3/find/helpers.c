// Helper functions

#include <cs50.h>

#include "helpers.h"

// Returns true if value is in array of n values, else false
bool search(int value, int values[], int n)
{
    if (n < 0)
    {
        return false;
    }
    int min = 0;
    int max = n - 1;
    while(min <= max)
    {
        int i = min + (max - min) / 2;
        if (values[i] == value)
        {
            return true;
        }
        else if (values[i] < value)
        {
            min = i + 1;
        }
        else
        {
            max = i - 1;
        }
    }
    return false;
}

// Sorts array of n values
void sort(int values[], int n)
{
    // TODO: implement an O(n^2) sorting algorithm
    int num;
    int j;
    for (int i = 1; i < n; i++)
    {
        num = values[i];
        j = i - 1;
        while (j >= 0 && values[j] > num)
        {
            values[j + 1] = values[j];
            j = j - 1;
        }
        values[j + 1] = num;
    }
    return;
}
