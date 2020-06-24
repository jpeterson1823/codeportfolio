def get_intcode():
    return [int(i) for i in open("day2.data").readline().split(",")]

def read_intcode(code):
    pos = 0
    while pos <= len(code):
        if code[pos] == 99:
            break
        elif code[pos] == 1:
            code[code[pos+3]] = code[code[pos+1]] + code[code[pos+2]]
        elif code[pos] == 2:
            code[code[pos+3]] = code[code[pos+1]] * code[code[pos+2]]
        else:
            print("Unknown opcode at " + str(pos) + " with value " + str(code[pos]) + ".")
            exit(1)
        pos += 4
    return code[0]

def main():
    intcode = [int(i) for i in open("day2.data").readline().split(",")]

    target = 19690720

    for noun in range(0,99):
        for verb in range(0,99):
            code = get_intcode()
            code[1] = noun
            code[2] = verb
            if read_intcode(code) == target:
                print(str(100 * noun + verb))
                exit()

if __name__ == "__main__":
    main()