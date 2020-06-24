import os
import getpass
import re
import shutil

# This script checks to make sure that AnarchyGrabber3 is not installed on a Discord client.
# If AnarchyGrabber3 is found to be installed, then script asks if user would like to delete the Discord
#   folder. Script only deletes files if user elects for script to do so.

path = "C:/Users/"+getpass.getuser()+"/AppData/Roaming/Discord"
if not os.path.exists(path) : print("Could not locate folder: "+path); exit()
subfolders = [f.path for f in os.scandir(path) if f.is_dir()]
for folder in subfolders:
    foldername = folder.split("\\")[len(folder.split("\\"))-1]
    if re.match("\d+\.\d+\.\d+", foldername).group(0) != None:
        path += "/"+foldername
        break;

path += "/modules/discord_desktop_core/index.js"

if not os.path.isfile(path):
    print("Could not locate "+path)

index = open(path, 'r').read()
if index != "module.exports = require('./core.asar');":
    response = input("Most likely infected.\nWould you like me to completely wipe your Discord installation out of existence? (Y/N)")
    while response.lower() != 'y' or response.lower() != 'n' : response = input("Please type Y or N for your answer: ")
    if response.lower() == 'n':
        print("Alrighty then, make sure to remove it yourself then. Goodbye.")
        exit(0);
    else:
        input("Deleting all files in: C:/Users/"+getpass.getuser()+"/AppData/Roaming/Discord\nPress any key to continue OR press CTRL + C to stop.")
        shutil.rmtree("C:/Users/"+getpass.getuser()+"/AppData/Roaming/Discord")
        print("Discord has been wiped from your system. Please reinstall by going to the official website.")
        print("C H A N G E  Y O U R  P A S S W O R D !")
else:
    print("Everything checks out. You're good to go!")
    print("See ya!")
    exit();