#!/usr/bin/env python3
# Version 1.3, updated on 1/15/2020
import os, sys, platform, getpass, re, binascii
import PySimpleGUI as sg

def get_os():
    # Returns the os of the system according to this list
    PLATFORMS = {
        'linux1' : 'Linux',
        'linux1' : 'Linux',
        'darwin' : 'OS X',
        'win32'  : 'Windows'
    }
    return sys.platform if sys.platform not in PLATFORMS else PLATFORMS.get(sys.platform)

def get_path():
    # Returns the proper path based on the OS detected.
    plat = get_os()
    if plat == 'Windows' : return 'Users\\'+getpass.getuser()
    elif plat == 'OS X' or plat == 'Linux' : return os.path.expanduser('~')
    else : return os.getcwd()

def get_asgn_list(file):
    # Returns the decoded data within the file as a list
    lines = str_decode(file.read()).split(';')
    # ^^^ This decodes the data from Base64, but the default method throws on a '\n' char at the end, so you must remove it

    file.close()
    return [line.split('.') for line in lines]

def group(arr):
    # Returns a grouped dictionary based on class rather than a random list of tuples
    grouped = {'calc':[],'phys':[],'film':[],'wgeo':[],'apcs':[],'engl':[],'misc':[]}
    for asgn in arr:
        if len(asgn[0]) > 0:
            grouped[asgn[0]].append(asgn[1])
    return grouped

def get_updated_layout(asgn, FONT):
    # This returns the updated layout for the window.
    layout = []

    if len(asgn['calc']) > 0:       # Layout for the calculus assignment tab
        calc_layout = []
        for assignment in asgn['calc']:
                calc_layout.append([sg.Checkbox(assignment, font=FONT, key='calc.'+assignment)])
        layout.append([sg.Frame(layout=calc_layout,title='AP Calculus I',font=FONT)])
    
    if len(asgn['phys']) > 0:       # Layout for the physics assignment tab
        phys_layout = []
        for assignment in asgn['phys']:
                phys_layout.append([sg.Checkbox(assignment, font=FONT, key='phys.'+assignment)])
        layout.append([sg.Frame(layout=phys_layout,title='AP Physics C',font=FONT)])
    
    if len(asgn['film']) > 0:       # Layout for the film studies assignment tab
        film_layout = []
        for assignment in asgn['film']:
                film_layout.append([sg.Checkbox(assignment, font=FONT, key='film.'+assignment)])
        layout.append([sg.Frame(layout=film_layout,title='Film Studies',font=FONT)])
    
    if len(asgn['wgeo']) > 0:       # Layout for the world geography assignment tab
        wgeo_layout = []
        for assignment in asgn['wgeo']:
                wgeo_layout.append([sg.Checkbox(assignment, font=FONT, key='wgeo.'+assignment)])
        layout.append([sg.Frame(layout=wgeo_layout,title='World Geography',font=FONT)])
    
    if len(asgn['apcs']) > 0:       # Layout for the APCSP assignment tab
        apcs_layout = []
        for assignment in asgn['apcs']:
                apcs_layout.append([sg.Checkbox(assignment, font=FONT, key='apcs.'+assignment)])
        layout.append([sg.Frame(layout=apcs_layout,title='AP Computer Science Principles',font=FONT)])
    
    if len(asgn['engl']) > 0:       # Layout for the english assignment tab
        engl_layout = []
        for assignment in asgn['engl']:
                engl_layout.append([sg.Checkbox(assignment, font=FONT, key='engl.'+assignment)])
        layout.append([sg.Frame(layout=engl_layout,title='English IV Honors',font=FONT)])

    if len(asgn['misc']) > 0:       # Layout for the miscellaneous assignment tab
        misc_layout = []
        for assignment in asgn['misc']:
                misc_layout.append([sg.Checkbox(assignment, font=FONT, key='misc.'+assignment)])
        layout.append([sg.Frame(layout=misc_layout,title='Miscellaneous',font=FONT)])

    layout.append([sg.Button('Submit Changes',font=FONT), sg.Button('Submit Changes & Exit',font=FONT), sg.Button('Exit',font=FONT)])


    # Layout for the addition option tab
    layout.append([sg.Frame(layout=[
                [sg.OptionMenu(('Select Class','AP Calculus','AP Physics C','Film Studies','World Geography','AP Computer Science Principles','English IV Honors','Miscellaneous'),size=(42,6),key='course'),sg.Submit('Add',font=FONT)],
                [sg.Input(font=FONT,key='assignment')]
            ],title='Add New Assignment', font=FONT)])

    return layout

def str_encode(string):
    return binascii.b2a_base64(binascii.b2a_hex(string.encode('utf-8'))).decode('ascii')

def str_decode(string):
    return binascii.a2b_hex(binascii.a2b_base64(string.encode('utf-8'))).decode('ascii')

def main():
    size = 14 if get_os() == 'OS X' else 12

    FONT = ('Avenir', size)

    # Get the path of the asgn folder housing the data and icon
    PATH = get_path()
    os.chdir(PATH)
    CLASSES = {
        'calc' : 'AP Calculus',
        'phys' : 'AP Physics C',
        'film' : 'Film Studies',
        'wgeo' : 'World Geography',
        'apcs' : 'AP Computer Science Principles',
        'engl' : 'English IV Honors',
        'misc' : 'Miscellaneous'
    }

    # Check if folder is there. If not, create said folder and install appropriate files.
    if os.path.isdir('./asgn'):
        os.chdir('./asgn')
        file = open('asgnv2.data','r+')
    else:
        print('ASGN not installed, creating installation folder.')
        os.mkdir('./asgn')
        os.chdir('./asgn')
        file = open('asgnv2.data','w+')
    

    # vvvv This bit of code creates a variable housing the decoded data as a string to log changes that will need to be saved,
    #           rather than writing to the file each time a change is made
    asgn_file = open('asgnv2.data'); asgn_data = str_decode(asgn_file.read()); asgn_file.close()
    asgn = group(get_asgn_list(file))

    # vvvv This is not supported for OS X for some reason. Bad module.
    sg.ChangeLookAndFeel('DarkAmber')
    sg.SetOptions(font=FONT)

    # Create window with icon, seems to not work with OS X.
    window = sg.Window('Assignments',icon='icon.png').Layout(get_updated_layout(asgn,FONT))

    while True:
        # Read values for the checkboxes
        event, values = window.Read()

        if event in (None,'Exit'):
            exit()


        # If 'Submit Changes' is pressed.
        elif event in ('Submit Changes', 'Submit Changes & Exit'):
            # Create a list to monitor what needs to be removed, meaning completed assignments.
            remove_list = []

            # Loop through all possible assignments and check if they have been completed. If so, remove them.
            for course in asgn.keys():
                for assignment in asgn[course]:
                    if values[course+'.'+assignment] == True:
                        # Use regex to find and remove the assignment from decoded string
                        asgn_data = re.sub(course+'\.'+assignment+';','',asgn_data)

                        # Append the completed assignment to the removal list.
                        remove_list.append(course+'.'+assignment)
            
            # Remove assignments from asgn list that are in the removal list
            for item in remove_list:
                course = item.split('.')[0]
                assignment = item.split('.')[1]
                asgn[course].pop(asgn[course].index(assignment))

            file = open('asgnv2.data','w+')
            
            # Write the changes to the file with Base64 encoding.
            file.write(str_encode(asgn_data))
            
            # Refresh window by closing it then reopening it in the same place.
            # I am unsure if there is another method of doing so since documentation of the PySimpleGUI module is sparce.
            win_loc = window.CurrentLocation()
            window.Close()
            window = sg.Window('Assignments',icon='icon.png',location=win_loc).Layout(get_updated_layout(asgn, FONT))

            if event == 'Submit Changes & Exit':
                exit()

        # If 'Add' button is pressed
        elif event in ('Add'):
            # Get assignment information.
            course = values['course']
            assignment = values['assignment']

            # Making sure the class that is check off isn't somehow glitched into oblivion.
            if course in CLASSES.values():

                # vvvv  This looks throught CLASSES to find the shortened name of the assignment's course's name.
                # vvvv  AKA finding engl for English IV
                course = list(CLASSES)[list(CLASSES.values()).index(course)]

                # Add assignment to the asgn list.
                asgn[course].append(assignment)

                # Add assignment to the decoded file string
                asgn_data+=course+'.'+assignment+';'

                file = open('asgnv2.data','w+')

                # Write changes to file using Base64 encoding.
                file.write(str_encode(asgn_data))
                file.close()
                
                # Again, refresh the window in this super odd method bc there is no other way to do so to my knowledge.
                win_loc = window.CurrentLocation()
                window.Close()
                window = sg.Window('Assignments',icon='icon.png',location=win_loc).Layout(get_updated_layout(asgn, FONT))
                    

if __name__ == '__main__':
    main()