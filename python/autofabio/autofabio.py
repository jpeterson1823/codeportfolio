from tika import parser
import convertapi, os, re

'''
***** Error Codes *****
1: Extra file found in the 'ws and ppt' folder.
2: Unsupported file type found in 'ws and ppt' folder.
3: Failed to locate either the powerpoint file or the worksheet pdf file.
4: Failed to convert ppt to pdf for unknown reason(s).
5: Failed to extract data from ppt.
6: Failed to extract data from ws pdf.
'''

def convert(file_path):
    try:
        convertapi.api_secret = 'PJZWgrsniAuwWt1P'
        convertapi.convert('pdf', {
            'File': file_path
        }, from_format = 'ppt').save_files('./converted')
    except:
        print('Failed to convert ppt to pdf. Contact me for support.')
        exit(4)
    
    if os.path.isfile('./converted/'+file_path.split('/')[2][:len(file_path.split('/')[2])-2]+'df'):
        return True
    else:
        return False


files = os.listdir('./ws and ppt')
if len(files) != 2:
    print('Make sure there are only 2 files in the \'ws and ppt\' folder. Please try again.'); exit(1)

ppt_path = '+-+'
ws_path = '+-+'
for file in files:
    if file.split('.')[1] == 'ppt':
        ppt_path = './ws and ppt/' + file
        print('Found ppt...')
    elif file.split('.')[1] == 'pdf':
        ws_path = './ws and ppt/' + file
        print('Found pdf...')
    else:
        print('Unsupported file type in \'ws and ppt\'. There should be a \'.ppt\' file and a \'.pdf\' file in the \'ws and ppt\' folder. Remove the unsupported file and try again.'); exit(2)

# Attempt to locate the ppt files and ws files
failed = False
if ppt_path == '+-+':
    print('Failed to locate the powerpoint file. Please check the \'ws and ppt\' folder and try again.'); failed=True
if ws_path == '+-+':
    print('Failed to locate the worksheet pdf file. Please check the \'ws and ppt\' folder and try again.'); failed=True
if failed: exit(3)

#Read the ppt and ws files
print('Extracting text from ppt and pdf files...')
pptt = parser.from_file(ppt_path)['content']
if pptt == 'None':
    print('Failed to read ppt file, attempting to convert to pdf...')
    convert(ppt_path)
    ppt_path = './converted/' + ppt_path.split('/')[2][:len(ppt_path.split('/')[2])-2]+'df'   #fixing the ppt path
    print('Successfully converted ppt to pdf. Extracting text from ppt...')
    pptt = parser.from_file(ppt_path)['content']
    if pptt != 'None':
        print('Successfully extracted text from ppt.')
    else:
        print('Failed to extract text from powerpoint pdf. I actually don\'t know why this would happen, so contact me.')
        exit(5)
else:
    print('Successfully extracted text from ppt.')

#fix formatting of ppt bc fabio did some weird spacing stuff.
pptt = re.sub('[\s]+', ' ', pptt)
pptt = re.sub('[\,?\n]+', '.\n', pptt)


wst = parser.from_file(ws_path)['content']
wst = re.sub('Name: [_]+', '', wst)        #Remove name blanks and other odd fabio stuffs
wst = re.sub('[\s]+', ' ', wst)
if wst == 'None':
    print('Failed to extract text from worksheet pdf. This is mostlikely due to corruption of the pdf. Contact me for support.')
    exit(6)
else:
    print('Successfully extracted text from worksheet.')

#Locate all blanks in ws and remove unnecessary spaces and '\n' found in text.
wst_blanks = re.findall("[\s?\w\s?]+[_]+[\w\s]+\.?",wst)
# wst_blanks = re.findall("[\s?\w\s?]*[_]+[\s?\w\s?]*[,;.?]",wst)
for i in range(len(wst_blanks)):
     wst_blanks[i] = wst_blanks[i].strip().replace('\n', '')
     wst_blanks[i] = re.sub('[_]+', '<blank>',wst_blanks[i])
     wst_blanks[i] = re.sub('[_]+', '<blank>',wst_blanks[i])

#Remove extra found blanks.
temp = wst_blanks
for blank in temp:
    if blank == '<blank>' or 'Name' in blank:
        wst_blanks.remove(blank)

#Split up sentances with multiple blanks into seperate objects in a list
temp = wst_blanks
for blank in temp:
    splitblank = blank.split('<blank>')
    if len(splitblank) > 2:
        #print(blank)
        wst_blanks.remove(blank)
        wst_blanks.append(splitblank[0]+'<blank>'+splitblank[1])
        #print(splitblank[0]+' <blank> '+splitblank[1])
        wst_blanks.append(splitblank[1]+'<blank>'+splitblank[2])
        #print(splitblank[1]+' <blank> '+splitblank[2])

print("Searching for answers...")
answers = []
for blank in wst_blanks:
    spt = blank.split('<blank>')
    if spt[1] == '': spt[1] = ' '
    regex = spt[0] +'.*'+spt[1]
    match = re.findall(regex, pptt)
    answers.append(match[0].split('.')[0].strip()) if len(match) > 0 else answers.append('[?]')

boutput = open('./output/blanks.txt', 'w+')
[boutput.write(str(line)+'\n') for line in wst_blanks]
boutput.close()

aoutput = open('./output/answers.txt', 'w+')
[aoutput.write(str(line)+'\n') for line in answers]

pptoutput = open('./output/ppt.txt', 'w+')
pptoutput.write(pptt)
pptoutput.close()

wsoutput = open('./output/ws.txt', 'w+')
wsoutput.write(wst)
wsoutput.close()
# print(wst_blanks)
# print(answers)