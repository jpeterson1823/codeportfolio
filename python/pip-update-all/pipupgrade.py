import os, sys, getpass

# Constants for script.
USAGE_STR = "Usage: pipupgrade.py [-a,-p]"
SUPPORTED_PLATFORMS = {
    "linux1" : "Linux",
    "linux1" : "Linux",
    "darwin" : "OS X",
    "win32"  : "Windows"
}

# Determine OS running on host.
OS = None if sys.platform not in SUPPORTED_PLATFORMS else SUPPORTED_PLATFORMS.get(sys.platform)

if OS == "Windows":
    PATH = "\\User\\"+getpass.getuser()
elif OS == "OS X" or "Linux":
    PATH = os.path.expanduser("~")
else:
    PATH = None
    print("Unsupported OS detected, exiting...")
    exit(1)

# Chdir into host-home/pipupgradebin if folder exist. If not, create it then chdir into it.
if not os.path.isdir(os.sep+PATH+os.sep+"pipupgradebin") : os.mkdir(os.sep+PATH+os.sep+"pipupgradebin")
os.chdir(os.sep+PATH+os.sep+"pipupgradebin")

# Upgrade pip packages.
os.system("pip list --outdated --format=freeze > reqs.update")
os.system("pip install -r reqs.update --upgrade")
print("Done.")