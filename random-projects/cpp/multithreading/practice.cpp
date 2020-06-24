#include <iostream>
#include <thread>
#include <chrono>

void foo(std::string str);
bool shouldStop();

bool stop = false;

int main()
{
    std::thread printLoop(foo, "Looping...");
    printLoop.detach();
    std::thread printToo(foo, "Ummmmm...");
    printToo.detach();

    std::this_thread::sleep_for(std::chrono::seconds(2));
    stop = true;
    std::this_thread::sleep_for(std::chrono::seconds(2));
    
    return 0;
}

void foo(std::string str) 
{
    while(!shouldStop()) {
        std::cout << str << std::endl;
    }
    std::cout << "Stopping..." << std::endl;
}

bool shouldStop() {
    if (stop) return true;
    else return false;
}
