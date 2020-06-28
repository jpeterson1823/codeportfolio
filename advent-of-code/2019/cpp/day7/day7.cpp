#include <iostream>
#include <vector>
#include <fstream>
#include <string>

using namespace std;


vector<int> getIntcode();
int readIntcode(vector<int> intcode);

int main()
{
    // Store intcode into vector
    vector<int> intcode = getIntcode();

    string phases = "01234";
    for (int count = 0; count < phases.length(); count++)
    {
       for (int i = 0; i < phases.size()-1; i++)
       {
           char temp = phases[i+1];
           phases[i+1] = phases[i];
           phases[i] = temp;
           cout << phases << endl;
       }
    } 
}





vector<int> getIntcode() 
{
    // Get puzzle input via ifstream
    vector<int> puzzleInput = { };

    ifstream puzzleInputFile ("7.in");
    string input;


    // Get line string from ifstream, then split line by delimiter ',' and adding to vector list.
    if (puzzleInputFile.is_open())
    {
        getline(puzzleInputFile, input);

        int lastIndex = 0;  // Keep track of spot after previous ',', keeping track of the new instruction.
        for (int i = 0; i < input.length(); i++)
        {
            if (input[i] == ',')    // Check for delimiter
            {
                puzzleInput.push_back(stoi(input.substr(lastIndex, i))); // Add the instruction/value to intcode.
                lastIndex = i+1;    // Set lastIndex to next number proceeding the current comma.
            }
        }
        puzzleInput.push_back(stoi(input.substr(lastIndex, input.length())));   // Add final instruction/value to intcode.
    }
    else printf("Failed to open puzzle input file.\n");

    return puzzleInput;
}


int readIntcode(vector<int> intcode)
{
    int index = 0;
    while (index < intcode.size())
    {
        // Get opcode and modes.
        int opcode = intcode[index]%100;
        int modec = intcode[index]/100%10;
        int modeb = intcode[index]/1000%10;
        int modea = intcode[index]/10000%10;

        //printf("Opcode: %i\nmodea: %i\nmodeb: %i\nmodec: %i\n", opcode, modea, modeb, modec);

        // Get parameters for processing instruction based on modes a-c.
        int param1, param2, param3;

        if (modec == 0) 
            param1 = intcode[intcode[index+1]];
        if (modec == 1) 
            param1 = intcode[index+1];

        if (modeb == 0)
            param2 = intcode[intcode[index+2]];
        if (modeb == 1)
            param2 = intcode[index+2];
        
        if (modea == 0)
            param3 = intcode[index+3];
        if (modea == 1)
            param3 = index+3;

        //printf("opcode: %i\nparam1: %i\nparam2: %i\nparam3: %i\n", opcode, param1, param2, param3);

        string input;   // Create variable for possible input from user.
        switch (opcode)
        {
            case 99:    // Exit
                index = intcode.size();
                break;

            case 1:     // Add  
                intcode[param3] = param1 + param2;
                index += 4;
                break;

            case 2:     // Multiply
                intcode[param3] = param1 * param2;
                index += 4;
                break;

            case 3:     // Input and store
                cout << "Input a value: ";
                cin >> input;
                intcode[intcode[index+1]] = stoi(input);
                index += 2;
                break;

            case 4:     // Print value at location
                cout << "Value: " << intcode[intcode[index+1]] << endl;
                index += 2;
                break;
            
            case 5:     // Do if true
                if (param1 != 0)
                    index = param2;
                else index += 3;
                break;
            
            case 6:     // Do if false
                if (param1 == 0)
                    index = param2;
                else index += 3;
                break;
            
            case 7:     // Less than?
                if (param1 < param2)
                    intcode[param3] = 1;
                else intcode[param3] = 0;
                index += 4;
                break;
            
            case 8:     // Equal to?
                if (param1 == param2)
                    intcode[param3] = 1;
                else intcode[param3] = 0;
                index += 4;
                break;

            default:
                cout << "Error has occured." << endl;
                printf("opcode: %i\nparam1: %i\nparam2: %i\nparam3: %i\n", opcode, param1, param2, param3);
                index = intcode.size();
                break;
        }
    }

    return intcode[0];
}
