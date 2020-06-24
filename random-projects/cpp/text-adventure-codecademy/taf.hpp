#include <iostream>

inline
void validate_input(std::string &input) {
    while (input != "1" && input != "2"){
        std::cout << "Invalid input. Please try again.\n";
        std::cin >> input;
    }
}

inline
void show_end() {
    std::cout << "=======\n";
    std::cout << "THE END\n";
    std::cout << "=======\n";
}