import os, sys, platform, pexpect

os.system("clear")
print("Please run this with sudo privilages.\nInstalling vote bot...")

if "Windows" in platform.platform():
    print("This bot is not supported for your OS quite yet. Exiting...")
    sys.exit(2)

elif "Mac" in platform.platform(): plat = "mac"
elif "Linux" in platform.platform(): plat = "lin"


print("Unzipping geckodriver for current system...")
if plat = "mac" : os.system("unzip ./src/mac-geckodriver.zip")

print("Copying geckodriver to /bin...")
os.system("cp ./src/geckodriver /bin")