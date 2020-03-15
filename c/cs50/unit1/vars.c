#include <stdio.h>
#include <cs50.h>


int main(void) {
    int x = get_int("Enter an Int: ");
    int y = get_int("Enter another Int: ");
    string first_name = get_string("Enter your first name: ");
    float gpa = get_float("Enter your GPA: ");
    printf("The variable x is %d and the variabe for y is %d. Your first name is %s and your GPA is %.2f\n", x, y, first_name, gpa);
}