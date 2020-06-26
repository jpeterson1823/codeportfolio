def readIntcode(intcode):
    index = 0
    while index < len(intcode):
        opcode = intcode[index]%100
        modec = intcode[index]/100%10
        modeb = intcode[index]/1000%10
        modea = intcode[index]/10000%10

        param1 = intcode[intcode[index+1]] if modec == 0 else intcode[index+1]
        param2 = intcode[intcode[index+2]] if modeb == 0 else intcode[index+2]
        param3 = intcode[intcode[index+3]] if modea == 0 else intcode[index+3]

        if opcode == 99:
            index = len(intcode)

        elif opcode == 1:
            intcode[param3] = param1 + param2
            index += 4

        elif opcode == 2:
            intcode[param3] = param1 * param2
            index += 4

        elif opcode == 3:
            i = input('Input: ')
            intcode[intcode[index+1]] = int(i)
            index += 2

        elif opcode == 4:
            print('Output: {}'.format(intcode[intcode[index+1]]))
            index += 2

        elif opcode == 5:
            if param1 != 0:
                index = param2
            else:
                index += 3

        elif opcode == 6:
            if param1 == 0:
                index = param2
            else:
                index += 3

        elif opcode == 7:
            if param1 < param2:
                intcode[param3] = 1
            else:
                intcode[param3] = 0
            index += 4

        elif opcode == 8:
            if param1 == param2:
                intcode[param3] = 1
            else:
                intcode[param3] = 0
            index += 4

        else:
            print('Error has occured.')
            print('Opcode: {}\nparam1: {}\nparam2: {}\nparam3: {}\n'.format(opcode, param1, param2, param3))
            index = len(intcode)
    
    print('Finished reading intcode')




def main():
    intcode = [int(x) for x in open('5.in').read().split(',')]
    readIntcode(intcode)


if __name__ == '__main__':
    main()
