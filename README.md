# MIPS-Assembly-Language-Emulator

This project is a simple simulator for an assembler module which can process code written using the MIPS instruction set as defined within the textbook ‘Computer Organization and Design: The hardware/Software Interface” 5th Edition, Patterson and Hennessy.

An implicit assumption is of Von Neuman model for the ISA. This simplified model is applied to reduce complexity and keep the project in scope to what its purpose is. The language of choice is Java but in this case, this was done as more of a preference rather than for any design reason. The scope of this effort is to simply build a simulator in order to better understand asssemblers.  

The simulator should run in one of two different modes. The first line of the binary code is either 0 or 1. if 0, the simulator simulates the program until the end and then print the contents of the 32 register $0 - $31. If the first line is 1, the simulator prints the contents of the 32 registers after it executes any instruction.

The code was meant to be run within an IDE like Eclipse which can support development and execution of source code.
