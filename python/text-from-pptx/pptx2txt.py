from tika import parser
import re

file = "/home/john/Documents/GitHub/codeportfolio/python/text-from-pptx/geoch19.ppt"

file_data = parser.from_file(file)
text = file_data['content']

text = re.sub("Click the mouse button or press the \nSpace Bar to display the information\.", "", text)
text = re.sub("\(pages \d\d\d\d?–\d\d\d\d?\)", "", text)
text = re.sub("\n*Presentation Plus!\n*Splash Screen\n*", "", text)
text = re.sub("+", "", text)
text = re.sub("[\n]+", "", text)
text = re.sub("\(cont\.\)", "\n", text)
text = re.sub("\S\t", "\g<0>\n\t", text)
text = re.sub("Section \d\d?-\d\d?", "\n\n\g<0>\n", text)
text = re.sub("\.[\s*]?", ".\n", text)

notes = open("./notes.txt","w")
notes.write(text)
notes.close()