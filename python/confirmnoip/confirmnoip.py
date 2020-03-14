from selenium import webdriver
from selenium.webdriver.firefox.options import Options

opts = Options()
opts.headless = True
assert opts.headless

#browser = webdriver.Firefox(executable_path="./src/geckodriver", options=opts)
browser = webdriver.Firefox(executable_path="./src/geckodriver")
browser.get("https://www.noip.com/")

browser.find_element_by_xpath("/html/body/section[2]/div/ul/li[1]/a").click()
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/input[1]").send_keys("petersonjohn418@gmail.com")
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/input[2]").send_keys("1823dnsfree*")
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/button").click()

input("Press ENTER to continue...")
browser.close()
exit(0)