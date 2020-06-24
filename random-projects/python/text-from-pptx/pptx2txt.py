from tika import parser
import re, os

file = input("Path to ppt: ")

if not os.path.isfile(file):
    print("Location does not exist. Please check the path provided and try again.")

file_data = parser.from_file(file)
text = file_data['content']

text = re.sub("Click the mouse button or press the \nSpace Bar to display the information\.", "", text)
text = re.sub("\(pages \d\d\d\d?–\d\d\d\d?\)", "", text)
text = re.sub("\n*Presentation Plus!\n*Splash Screen\n*", "", text)
text = re.sub("\n+", " ", text)
text = re.sub("Section \d\d?-\d\d?\s?\t?", "\n\n\g<0>\n\t", text)
text = re.sub("\s?[\s?]+", "\n\t", text)
text = re.sub("\. +", ".\n\t", text)
text = re.sub("\t\t", "\t", text)
text = re.sub("\t\n", "\n", text)
text = re.sub("\t.+\t", "\t",text)

# Special cases that I find as I go along.
text = re.sub("sq\.\n\tkm", "sq. km", text)

notes = open("./notes.txt","w")
notes.write(text)
notes.close()