CXXFLAGS = --std=c++17

garden: main.o garden.o plant.o light.o *.h
	$(CXX) $(CXXFLAGS) main.o garden.o plant.o light.o -o garden
	@printf "Now type ./garden to execute the result\n\n"
garden.o: garden.cpp *.h
	$(CXX) $(CXXFLAGS) -c garden.cpp
light.o: light.cpp *.h
	$(CXX) $(CXXFLAGS) -c light.cpp
main.o: main.cpp *.h
	$(CXX) $(CXXFLAGS) -c main.cpp
plant.o: plant.cpp *.h
	$(CXX) $(CXXFLAGS) -c plant.cpp
clean:
	rm -f *.o *.gch a.out  garden light main plant

