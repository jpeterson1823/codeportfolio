#include <iostream>

inline
void display_board(char board[3][3]) {
    // This prints the board info along with the board's skeleton.
    std::cout << "   1   2   3\n";
    std::cout << "A  " << board[0][0] << " | " << board[0][1] << " | " << board[0][2] << "\n";
    std::cout << "  ===+===+===\n";
    std::cout << "B  " << board[1][0] << " | " << board[1][1] << " | " << board[1][2] << "\n";
    std::cout << "  ===+===+===\n";
    std::cout << "C  " << board[2][0] << " | " << board[2][1] << " | " << board[2][2] << "\n";
}

inline
char get_turn(int turn) {
    if (turn % 2 == 0) return 'O';
    else return 'X';
}

char check_win(char board[3][3]) {
    if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X') return 'X';
    else if (board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X') return 'X';
    else if (board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X') return 'X';

    else if (board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X') return 'X';
    else if (board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X') return 'X';
    else if (board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X') return 'X';

    else if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') return 'X';
    else if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') return 'X';


    else if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O') return 'O';
    else if (board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O') return 'O';
    else if (board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O') return 'O';

    else if (board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O') return 'O';
    else if (board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O') return 'O';
    else if (board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O') return 'O';

    else if (board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O') return 'O';
    else if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') return 'O';


    else return ' ';
}