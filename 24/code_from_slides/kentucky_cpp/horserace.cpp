#include <iostream>
#include <thread>
#include <chrono>
#include <ctime>
#include <algorithm>
#include <vector>
#include <array>
#include "horse.h"
#include "color.h"

const int HORSES = 20;
const int TRACK_LENGTH = 40;

int main() {
    // Randomize the pseudorandom number generator
    srand(time(NULL));

    // Pick random names for the horses (based mostly on Kentucky Derby winners)
    std::vector<std::string> names {
        "Legs of Spaghetti",
        "Ride Like the Calm",
        "Duct-taped Lightning",
        "Flash Light",
        "Speedphobia",
        "Cheat Ah!",
        "Go For Broken",
        "Whining Racer",
        "Spectacle",
        "Cannons a'Boring",
        "Plodding Prince",
        "Lucky Snooze",
        "Wrong Way",
        "Fawlty Powers",
        "Broken Tip",
        "American Zero",
        "Exterminated",
        "Great Regret",
        "Manual",
        "Lockout",
    };
    std::random_shuffle(names.begin(), names.end());
    names.push_back("2 Biggaherd"); // Default name for "too many"
    
    // Our competitors, to be assigned random names and speeds (smaller is faster)
    std::vector<Horse> horses;

    for (int i=0; i<HORSES; ++i) 
        horses.push_back(Horse{names[std::min(i, static_cast<int>(names.size())-1)], 100 + std::rand() % 100, TRACK_LENGTH});
    
    for(int i=0; i<120; ++i) std::cout << '\n';
    for (Horse& h : horses) std::cout << h.view() << std::endl;
    std::cout << "Awaiting the starting gun...";
    std::string line;
    std::getline(std::cin, line);

    // Instance a thread for each horse, which immediately gallops off asynchronously relative to main()
    std::array<std::thread, HORSES> threads;
    for (int i=0; i<HORSES; ++i) 
        // C++ lambda - copy i and reference everything else, calling gallop for each horse
        threads[i] = std::thread{[&,i] {horses[i].gallop();}};
        // In pre-lambda C++, we'd convert the method to a function old-school, 
        //   with the method address and a struct parameter like this:
        //   threads[i] = std::thread{&Horse::gallop, std::ref(horses[i])};


    // Display the horse track as the race runs
    bool running = true;
    while (running) {
        for(int i=0; i<120; ++i) std::cout << '\n';
        for (Horse& h : horses) {
            running &= h.running();  // When any horse stops running, stop updating the display
            std::cout << h.view() << std::endl;  // Display this horse's position on the track
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(100));
    }
        
    // Join the threads (let all of the horses come to a stop)
    for (std::thread& t : threads) t.join();

    // Announce the winner!
    std::cout << Color::GREEN << "\n###\n### The winner is " << Horse::winner() << "\n###\n" << Color{} << std::endl;
}

