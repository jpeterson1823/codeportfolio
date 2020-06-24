import requests, re, zipfile, shutil, os

server_dir = './journey\'s-end'

# Get current version from file.
current_version = int(open('./bin/current_version.txt', 'r').read())

# Download wiki page that contains the downloads, then use regex to get all download links for the server files.
open('./bin/html_page','wb').write(requests.get("https://terraria.gamepedia.com/Server", allow_redirects=True).content)
terraria_links = re.findall('https:\/\/www.terraria.org\/system\/dedicated_servers.+.zip\?\d+', open('./bin/html_page','r').read())

#print(terraria_links)

# Get available versions from list of links gathered above
available_versions = []
for link in terraria_links:
    available_versions.append(int(re.search('-\d+', link).group(0).strip('-')))

# Determine if server is out of date by comparing the version number of the file link to the current version
# stored in the 'current_version.txt' file.
if max(available_versions) <= current_version : print("Server is up to date.")
else :
    print("Updating server to Terraria "+str(max(available_versions))+'.\nDownloading server files from '+terraria_links[len(terraria_links)-1]+'...')
    open('./bin/terraria-server-'+str(max(available_versions))+'.zip', 'wb').write(requests.get(terraria_links[len(terraria_links)-1]).content)

    # Remove old server files.
    print('Removing old server files from '+server_dir+'...')
    folder = './journey\'s-end'
    for filename in os.listdir(folder):
        file_path = os.path.join(folder, filename)
        try:
            if os.path.isfile(file_path) or os.path.islink(file_path):
                os.unlink(file_path)
            elif os.path.isdir(file_path):
                shutil.rmtree(file_path)
        except Exception as e:
            print('Failed to delete %s. Reason: %s' % (file_path, e))

    # Unzip file for copying.
    print('Unziping server files...')
    with zipfile.ZipFile('./bin/terraria-server-'+str(max(available_versions))+'.zip','r') as zip_ref:
        zip_ref.extractall('./bin')

    # Copy files into server dir.
    print('Copying Linux server files to '+server_dir+'...')
    files = os.listdir('./bin/1404/Linux')
    for file in files :
        name = os.path.join('./bin/1404/Linux', file)
        if os.path.isfile(name) :
            print('\tcopying '+name)
            shutil.copy(name, './journey\'s-end')
        elif os.path.isdir(name) :
            print('\tcopying '+name+'/*')
            shutil.copytree(name, './journey\'s-end/'+name.split('/')[len(name.split('/'))-1])

    print('Cleaning up:')
    print('\tremoving ./bin/'+str(max(available_versions)))
    shutil.rmtree('./bin/'+str(max(available_versions)))
    print('\tremoving ./bin/terraria-server-'+str(max(available_versions)))
    os.unlink('./bin/terraria-server-'+str(max(available_versions))+'.zip')
    print('\tremoving ./bin/html_page')
    os.unlink('./bin/html_page')

    # Update the 'current_version.txt' listed version to the newly installed version.
    open('current_version.txt', 'w').write(str(max(available_versions)))

    print('Update Completed. Please remember to update the server file permissions.')
    