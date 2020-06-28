def readIntcode(intcode):
    # Pointer for intcode
    pointer = 0

    while True:
        inst = intcode[pointer]
        opcode = int(inst%100)
        c = int(inst/100%10)
        b = int(inst/1000%10)
        a = int(inst/10000%10)
        

        # Check for opcode 3 and 4 to avoid index overflow.
        if opcode == 3:
            intcode[intcode[pointer+1]] = int(input('Input: '))
            pointer += 2 

        elif opcode == 4:
            print('Output: '+str(intcode[intcode[pointer+1]]))
            pointer += 2
        
        
        else:
            if opcode == 99:
                break

            p1 = intcode[intcode[pointer+1]] if c == 0 else intcode[pointer+1]
            p2 = intcode[intcode[pointer+2]] if b == 0 else intcode[pointer+2]
            p3 = intcode[pointer+3] if a == 0 else pointer+3

            if opcode == 1:
                intcode[p3] = p1 + p2
                pointer += 4

            elif opcode == 2:
                intcode[p3] = p1 * p2
                pointer += 4

            elif opcode == 5:
                if p1 != 0:
                    pointer = p2
                else:
                    pointer += 3

            elif opcode == 6:
                if p1 == 0:
                    pointer = p2
                else:
                    pointer += 3

            elif opcode == 7:
                if p1 < p2:
                    intcode[p3] = 1
                else:
                    intcode[p3] = 0
                pointer += 4

            elif opcode == 8:
                if p1 == p2:
                    intcode[p3] = 1
                else:
                    intcode[p3] = 0
                pointer += 4

            else:
                print('Error in opcode at pointer='+str(pointer)+', opcode='+str(opcode))
                break



        
def main():
    intcode = [int(x) for x in open('5.in').read().split(',')]
    readIntcode(intcode)

if __name__ == '__main__':
    main()
