height = 24

while height > 23 or height < 1:
    temp = input("Height: ")
    try:
        height = int(temp)
    except ValueError:
        height = height #do nothing

    if height == 0: exit()

spaces = height - 1
blocks = 2

line = ""

for i in range(height):
    for j in range(spaces):
        line += " "
    for j in range(blocks):
        line += "#"

    blocks += 1
    spaces -= 1
    print(line)
    line = ""