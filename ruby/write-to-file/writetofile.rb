require "open-uri"
require 'zip'

def extract_zip(file, destination)
  FileUtils.mkdir_p(destination)

  Zip::File.open(file) do |zip_file|
    zip_file.each do |f|
      fpath = File.join(destination, f.name)
      zip_file.extract(f, fpath) unless File.exist?(fpath)
    end
  end
end

# Terraria updater but in Ruby rather than Python, for practice mostly
url = "https://terraria.gamepedia.com/Server"
server_dir = "./test-server"

# Get current version
current_version = File.read("./bin/current.txt").to_i

# Download homepage HTML
File.open("./bin/html", "w") { |f| f.write open(url).read << +'\n'}

# Get server files links
server_links = []
File.read("./bin/html").scan(/https?:\/\/w?w?w?.?terraria.org\/s.+"/) { |link| server_links << link.chop}

# Check for update via versions in links
versions = []
server_links.each { |link| link.scan(/\d+\./) { |version| versions << version.chop.to_i } }
update = versions.max > current_version ? true : false

if update == false
    puts "Server is up to date: Terraria version " << current_version.to_s << '.'
    FileUtils.rm_rf("./bin/html")
else
    puts "Updating server to version " << versions.max.to_s
    puts "Downloading server files from " << server_links[versions.find_index(versions.max)] << "..."
    File.open("./bin/terraria-server-"+versions.max.to_s+".zip", "w") { |f| f.write open(server_links[versions.find_index(versions.max)]).read }

    puts "Deleting old server files in " << server_dir << "..."
    FileUtils.rm_rf(server_dir)
    FileUtils.mkdir_p(server_dir)

    puts "Extracting files to " << server_dir << "..."
    extract_zip("./bin/terraria-server-" << versions.max.to_s << ".zip",server_dir)
    FileUtils.cp_r(server_dir + "/" + versions.max.to_s + "/Linux/.", server_dir)

    puts "Deleting unnecessary files:"
    puts "\tremoving " << server_dir << "/1404"
    FileUtils.rm_rf(server_dir << "/1404")
    puts "\tremoving ./bin/terraria-server-" << versions.max.to_s << ".zip"
    FileUtils.rm_rf("./bin/terraria-server-" << versions.max.to_s << ".zip")
    puts "\tremoving ./bin/html"
    FileUtils.rm_rf("./bin/html")

    puts "Updating \'current.txt\' to newly installed version..."
    File.write("./bin/current.txt", versions.max.to_s)
    puts "Updated to Terraria version " << versions.max.to_s << ". Please remember to update the permissions of the server files!"
end