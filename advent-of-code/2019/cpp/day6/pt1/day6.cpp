#include <iostream>
#include <string>
#include <vector>
#include <fstream>

using namespace std;

struct planet
{
    string name;
    vector<string> children;

    planet(string pname, vector<string> pchildren = {})
        : name(pname), children(pchildren) {};
};


vector<string> getPuzzleInput()
{
    vector<string> puzzleInput = { };

    ifstream puzzleStream ("puzzleInput.txt");
    string line;
    if (puzzleStream.is_open())
    {
        while(getline(puzzleStream, line))
            puzzleInput.push_back(line);
    }

    return puzzleInput;
}


int main()
{
    vector<planet> planets = {};
    /*
    * TODO:
    *   implement splitting the planet input and creating planet vector
    */
}