#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const char parameters[4] = {'a','l','p','y'};

int charsInFile(char *path);
int linesInFile(char *path);

int main(int argc, char **argv)
{
    if (argc > 1)
    {
        printf("Other arguments are in developement!\n");
    }
    else
    {
        system("pip freeze > requirements.txt");
        int lines[linesInFile("requirements.txt")];
        FILE *requirements = fopen("requirements.txt","r");

        for (int i = 0; i < lines; i++)
        {
            char line[1000];

        }
    }
}

int charsInFile(char *path)
{
    FILE *file = fopen(path,'r');
    int count = 0;
    char c = fgetc(file);
    while (feof(file))
    {
        count+=1;
        c = fgetc(file);
    }
    fclose(file);
    return count;
}

int linesInFile(char *path)
{
    int lines = 1;
    FILE *file = fopen(path,'r');
    char c = fgetc(file);
    while (feof(file))
    {
        if (c == '\n') lines+=1;
        c = fgetc(file);
    }
    fclose(file);
    return lines;
}