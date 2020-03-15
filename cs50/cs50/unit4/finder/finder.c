// Recursively searches for a query in a directory.

//#define _BSD_SOURCE
//#define _GNU_SOURCE
#define _DEFAULT_SOURCE

#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <unistd.h>

#define MAXLENGTH 50

// Struct to hold the file name and type
typedef struct
{
    string name;
    string type;
}
path;

// Struct to hold the directory info
typedef struct
{
    string name;
    int npaths;
    path *paths;
}
directory;

// String to hold the word to seek
string key;

// The function to search for files in a directory and populate the struct
directory populate(directory dir);

// The function to recursively iterate through directories and handle files
int seek(directory dir);


int main(int argc, char *argv[])
{

    // TODO: ensure proper number of command line arguments

    if (argc > 3 || argc < 2)
    {
        printf("Usage: ./finder key dir");
        exit(1);
    }

    // Create initial directory and set name string
    directory dir;

    // TODO: set "dir"s name based on command line arguments entered
    key = argv[1];
    if (argc == 3)
    {
        dir.name = argv[2];
    }
    else
    {
        dir.name = "./";
    }
    //populate(dir);

    return seek(dir);
}

directory populate(directory dir)
{
    // Initialize all pointers and values in the given struct
    dir.npaths = 0;
    dir.paths = NULL;
    DIR *dirp;
    struct dirent *entry;

    dirp = opendir(dir.name);
    if (dirp == NULL)
    {
        printf("Opening directory failed. Check your input filepath!\n");
        return dir;
    }

    while ((entry = readdir(dirp)) != NULL)
    {
        if (entry->d_type == DT_DIR && strcmp(entry->d_name, ".") != 0 && strcmp(entry->d_name, "..") != 0)
        {
            // Allocate zeroed-out memory for the construction of the file name
            string name = calloc(1, strlen(dir.name) + strlen(entry->d_name) + 2);
            strcat(name, dir.name);
            strcat(name, entry->d_name);
            strcat(name, "/");

            // Reallocate memory to expand the array
            dir.paths = realloc(dir.paths, (dir.npaths + 1) * sizeof(path));

            // Add a new element to the array containing names and types
            path newPath = {.name = name, .type = "directory"};
            dir.paths[dir.npaths] = newPath;

            // Increase file count for the directory
            dir.npaths++;
        }

        // Else if entry is a file, increase file count and populate the struct
        else if (entry->d_type == DT_REG)
        {
            // Allocate zeroed-out memory for the construction of the file name
            string name = calloc(1, strlen(dir.name) + strlen(entry->d_name) + 1);
            strcat(name, dir.name);
            strcat(name, entry->d_name);

            // Reallocate memory to expand the array
            dir.paths = realloc(dir.paths, (dir.npaths + 1) * sizeof(path));

            // Add a new element to the array containing names and types
            path newPath = {.name = name, .type = "file"};
            dir.paths[dir.npaths] = newPath;

            // Increase file count for the directory
            dir.npaths++;
        }
    }

    // Close directory stream using system call closedir and return struct
    closedir(dirp);
    return dir;
}

// Recursive function to iterate through directories and search files
int seek(directory dir)
{
    dir = populate(dir);
    for (int i = 0; i < dir.npaths; i++)
    {
        if (strcmp(dir.paths[i].type, "directory") == 0)
        {
            directory newDir;
            newDir.name = dir.paths[i].name;
            seek(newDir);
        }
        else
        {
            char str[MAXLENGTH + 1];

            FILE *file = fopen(dir.paths[i].name, "r");

            while (fgets(str, MAXLENGTH, file) != NULL)
            {
                if (strstr(str, key) != NULL)
                {
                    FILE *found = fopen("./found.txt", "a");
                    fprintf(found, "%s\n", dir.paths[i].name);
                    printf("Found match!\n");
                    fclose(found);
                }
            }

            fclose(file);
        }
    }
    return 0;
}
