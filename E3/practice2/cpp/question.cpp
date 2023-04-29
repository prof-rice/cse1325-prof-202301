#include <iostream>
#include <vector>

class Question {
  public:
    Question(int points, std::string question)
        : _points{points}, _question{question} { }
    virtual std::string formatted_question()=0;

    int operator+(Question& q) {
        return _points + q._points;
    }
    friend int operator+(int p, Question& q) {
        return p + q._points;
    }
  protected:
    int _points;
    std::string _question;
};

class MultipleChoice : public Question {
  public:
    MultipleChoice(int points, std::string question, std::string answer)
        : Question{points, question}, _correct{answer} { }
    void add_incorrect(std::string answer) {
        _incorrect.push_back(answer);
    }
    std::string formatted_question() override {
        std::string fq = _question + "\n  __ " + _correct + '\n';
        for(auto i : _incorrect) fq += "  __ " + i + '\n';
        return fq;        
    }
  protected:
    std::string _correct;
    std::vector<std::string> _incorrect;
};

int main() {
    int points;
    std::cout << "Points? ";
    std::cin >> points; std::cin.ignore();
    
    std::string question;
    std::cout << "Question? ";
    std::getline(std::cin, question);
    
    std::string answer;
    std::cout << "Correct Answer? ";
    std::getline(std::cin, answer);
    
    MultipleChoice mc{points, question, answer};
    
    while(true) {
        std::cout << "Incorrect Answer? ";
        std::getline(std::cin, answer);
        if(answer.empty()) break;
        mc.add_incorrect(answer);
    }
    
    std::cout << "\nAnd the question is\n\n1. "
              << mc.formatted_question() << std::endl;
    
    MultipleChoice std{2, "Name?", "Prof Rice"};
    
    int total_points = mc + std;
    std::cout << "Total points: " << total_points << std::endl;
}

/* 

Example run:

Points? 3
Question? What is the name of the C++ compiler?
Correct Answer? g++
Incorrect Answer? c++
Incorrect Answer? cpp
Incorrect Answer? ccomp
Incorrect Answer? 

And the question is

1. What is the name of the C++ compiler?
  __ g++
  __ c++
  __ cpp
  __ ccomp

Total points: 5

*/
    
    
    
