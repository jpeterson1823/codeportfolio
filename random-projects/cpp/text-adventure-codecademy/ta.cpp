#include <iostream>
#include "taf.hpp"

int main() {

    std::string input;
    std::string name;
    
    std::cout << "================" << "\n";
    std::cout << "ASCII Adventure!" << "\n";
    std::cout << "================" << "\n";

    std::cout << "You wake up with a radiating pain in your head. You don't know where your memory is fuzzy.\n";
    std::cout << "You begin to realize now that you can't see a thing. It is pitch black.\n";
    std::cout << "You sit there for a minute or two and your eyes begin to adjust to the darkness.\n";
    std::cout << "You begin to look around as you see a light. Faint, but still visible.\nDo you walk towards it?\n\t1) Yes\n\t2) No\n";
    std::cin >> input;

    validate_input(input);

    if (input == "2") {
        std::cout << "\n\nAs you think about what the light might be, memory still fuzzy, you decide to avoid it entirely. Better to be safe than sorry.\n";
        std::cout << "You make a 180 and begin to walk. You walk for what seems like hours to no avail. You decide to re-evaluate you decision earlier.\n";
        std::cout << "Do you turn around and walk towards the light?\n\t1) Yes\n\t2) No\n";
        std::cin >> input;
        
        validate_input(input);

        if (input != "1") {
            std::cout << "You decide to stick with your initial gut decision. However, through your stubbornness, you died of thirst well before seeing anything else other than the light.\n";
            show_end();
            exit(0);
        }
        else {
            std::cout << "After re-evaluating your options, you decide that wondering aimlessly through the darkness is probably not the best of ideas.\n";
            std::cout << "You turn around and begin walking towards the light.\n";
        }
    }
    else {
        std::cout << "You decide that heading towards the only thing visible in this dark void is probably for the best.\n";
    }

    std::cout << "\nAs you approach the light, you begin to remember more and more about what had happened. You have a faint memory of laying down onto something, then an all encompassing darkness\n";
    std::cout << "takes hold of you. Your eyesight begins to fail, your breathing becomes more and more labored. You are having a panic attack. Suddenly, something calls your name.\n";
    std::cout << "Enter your name: ";
    std::cin >> name;

    std::cout << "\n";
    std::cout << "'" << name << ", can you hear me? " << name << "?'\n";
    std::cout << "You look around you but there is nothing to see except a white void. \nDo you respond?\n\t1) Yes\n\t2) No\n";
    std::cin >> input;
    validate_input(input);

    if (input == "2") {
        std::cout << "\nYou decide not to respond to the void calling your name.\n";
        std::cout << "After a few moments, the voice says, 'Well, if you wont respond, that must mean your courage would not be up to task. Such a pitty that a strong soul will be waisted yet again...'\n";
        std::cout << "As you begin to proccess what these words might mean, you are pulled into the floor and feel as if you are falling. Suddenly, you gasp\n";
        std::cout << "for air as you sit up on your couch, completely out of breath and heart racing. \n\nIt seems it was all a dream, but an odd one to say the least. What could it all mean...\n\n";

        show_end();
    }
    else {
        std::cout << "\nYou call out to the void, 'Yes, I can hear you. Where are you? No. What are you?'\n";
        std::cout << "The void responds with a calm, soothing, almost angelic like voice, 'You need not know what, why, or how, young one. However, heed my warning!\n";
        std::cout << "Your future holds a conflict of titans that you may not survive. Prepare! You must not be destroyed!'\n";
        std::cout << "The white light becomes blinding as you close your eyes and shield your face. When you open them again, you are staring at your cieling. You are on\n";
        std::cout << "the couch and must have dozed off. \n\n'What a weird dream,' you say, as you wonder what it all could have meant...\n\n";

        show_end();
    }

}