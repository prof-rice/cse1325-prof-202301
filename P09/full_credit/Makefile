CXXFLAGS = --std=c++17

vend: item.o vending_machine.o vend.o
	$(CXX) $(CXXFLAGS) item.o vending_machine.o vend.o -o vend

vend.o: vend.cpp *.h
	$(CXX) $(CXXFLAGS) -c vend.cpp -o vend.o

vending_machine.o: vending_machine.cpp *.h
	$(CXX) $(CXXFLAGS) -c vending_machine.cpp -o vending_machine.o

item.o: item.cpp *.h
	$(CXX) $(CXXFLAGS) -c item.cpp -o item.o


clean:
	-rm -f *.o *.gch *~ a.out vend
