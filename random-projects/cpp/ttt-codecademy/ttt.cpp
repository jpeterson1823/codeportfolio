#include <iostream>
#include "ttt.hpp"
#include <ctype.h>

int main() {

    // Tic Tac Toe!

    /*
    Array representing the board.
    Starts off with only spaces so that the board will be empty.
    */
    char board[3][3] =
    {
        {' ',' ',' '},
        {' ',' ',' '},
        {' ',' ',' '}
    };

    int turn = 1;
    std::string position;

    while (turn < 10) {

        display_board(board);
        std::cout << get_turn(turn) << "'s position: ";
        std::cin >> position;

        while (position.size() != 2 || !isalpha(position[0]) || isalpha(position[1])) {

            std::cout << "Invalid option. Please enter the letter of the row, then the number of the column.\n";
            std::cout << get_turn(turn) << "'s position: ";
            std::cin >> position;

        }

        if (toupper(position[0]) == 'A') board[0][((int)position[1]) - 49] = get_turn(turn);
        else if (toupper(position[0]) == 'B') board[1][(int)position[1] - 49] = get_turn(turn);
        else if (toupper(position[0]) == 'C') board[2][(int)position[1] - 49] = get_turn(turn);
        else board[0][(int)position[1]] = get_turn(turn);

        if (check_win(board) != ' ') turn = 10;

        turn++;

    }
    display_board(board);
    std::cout << check_win(board) << " wins!\n";
}