from PIL import Image

im = Image.open("src/untitled.png")
pixels = im.load()

# Icon dimensions:   80x80
icon_dim = 80

names = open("src/names.txt").read().split("\n")

# First src image
x = 9
y = 10
count = 0
for i in range(10):
    for i in range(9):
        img = im.crop((x,y,x+80,y+80))
        img.save("./ror2items/{}.png".format(names[count]))
        count+=1
        x += icon_dim + 8
    x = 9
    y+=icon_dim+8


# Second src image
x = 10
y = 4
im = Image.open("src/untitled1.png")
pixels = im.load()
for i in range(9):
    img = im.crop((x,y,x+80,y+80))
    img.save("./ror2items/{}.png".format(names[count]))
    count+=1
    x += icon_dim + 8

x = 10
y += icon_dim + 8

for i in range(5):
        img = im.crop((x,y,x+80,y+80))
        img.save("./ror2items/{}.png".format(names[count]))
        count+=1
        x += icon_dim + 8


