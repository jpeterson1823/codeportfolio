from tika import parser
import re

file = "/home/john/Documents/GitHub/codeportfolio/python/text-from-pptx/geoch19.ppt"

file_data = parser.from_file(file)
text = file_data['content']

text = re.sub("Click the mouse button or press the \nSpace Bar to display the information\.", "", text)
text = re.sub("\(pages \d\d\d\d?–\d\d\d\d?\)", "", text)
text = re.sub("\n*Presentation Plus!\n*Splash Screen\n*", "", text)
text = re.sub("\n\n\n", "\n", text)
text = re.sub("+", "", text)

notes = open("./notes.txt","w")
notes.write(text)
notes.close()