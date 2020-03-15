height = 24

# Check args
while height > 23 or height < 1:
    temp = input("Height: ")
    try:
        height = int(temp)
    except ValueError:
        height = height  # do nothing

    if height == 0:
        exit()

# Count spaces
spaces = height - 1
blocks = 1

line = ""

# Create image line-by-line
for i in range(height):
    for j in range(spaces):
        line += " "
    for j in range(blocks):
        line += "#"

    line += "  "

    for j in range(blocks):
        line += "#"

    blocks += 1
    spaces -= 1
    print(line)
    line = ""
