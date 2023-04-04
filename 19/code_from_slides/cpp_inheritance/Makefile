CXXFLAGS = --std=c++17

info:
	@echo
	@printf 'Type e.g., "make auto"  to create program "auto"\n'
	@printf 'Type       "make all"   to create every program\n'
	@printf 'Type       "make clean" to delete every program\n\n'

barnyard_animals_2: barnyard_animals_2.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals_2 barnyard_animals_2.cpp
	@printf "Now type ./barnyard_animals_2 to execute the result\n\n"

barnyard_animals: barnyard_animals.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals barnyard_animals.cpp
	@printf "Now type ./barnyard_animals to execute the result\n\n"

barnyard_animals_op_overload: barnyard_animals_op_overload.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals_op_overload barnyard_animals_op_overload.cpp
	@printf "Now type ./barnyard_animals_op_overload to execute the result\n\n"

barnyard_animals_op_overload_fixed: barnyard_animals_op_overload_fixed.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals_op_overload_fixed barnyard_animals_op_overload_fixed.cpp
	@printf "Now type ./barnyard_animals_op_overload_fixed to execute the result\n\n"

barnyard_animals_poly: barnyard_animals_poly.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals_poly barnyard_animals_poly.cpp
	@printf "Now type ./barnyard_animals_poly to execute the result\n\n"

barnyard_animals_pure_virtual: barnyard_animals_pure_virtual.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_animals_pure_virtual barnyard_animals_pure_virtual.cpp
	@printf "Now type ./barnyard_animals_pure_virtual to execute the result\n\n"

barnyard_simple: barnyard_simple.cpp
	-$(CXX) $(CXXFLAGS) -o barnyard_simple barnyard_simple.cpp
	@printf "Now type ./barnyard_simple to execute the result\n\n"

destructor: destructor.cpp
	-$(CXX) $(CXXFLAGS) -o destructor destructor.cpp
	@printf "Now type ./destructor to execute the result\n\n"

friends: friends.cpp
	-$(CXX) $(CXXFLAGS) -o friends friends.cpp
	@printf "Now type ./friends to execute the result\n\n"

multiple_inheritance: multiple_inheritance.cpp
	-$(CXX) $(CXXFLAGS) -o multiple_inheritance multiple_inheritance.cpp
	@printf "Now type ./multiple_inheritance to execute the result\n\n"

pure_virtual_bad: pure_virtual_bad.cpp
	-$(CXX) $(CXXFLAGS) -o pure_virtual_bad pure_virtual_bad.cpp
	@printf "Now type ./pure_virtual_bad to execute the result\n\n"

pure_virtual_fixed: pure_virtual_fixed.cpp
	-$(CXX) $(CXXFLAGS) -o pure_virtual_fixed pure_virtual_fixed.cpp
	@printf "Now type ./pure_virtual_fixed to execute the result\n\n"

all:  barnyard_animals_2 barnyard_animals barnyard_animals_op_overload barnyard_animals_op_overload_fixed barnyard_animals_poly barnyard_animals_pure_virtual barnyard_simple destructor friends multiple_inheritance pure_virtual_bad pure_virtual_fixed

clean:
	rm -f *.o *.gch a.out  barnyard_animals_2 barnyard_animals barnyard_animals_op_overload barnyard_animals_op_overload_fixed barnyard_animals_poly barnyard_animals_pure_virtual barnyard_simple destructor friends multiple_inheritance pure_virtual_bad pure_virtual_fixed

