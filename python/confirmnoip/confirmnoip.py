from selenium import webdriver
from selenium.webdriver.firefox.options import Options
from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_OAEP
import binascii, requests


def get_login():
    pvtkey = RSA.import_key(open("/home/invisa/private.key", "rb").read())
    url = "https://jpeterson1823.github.io/bG9naW4K.info"
    login = [binascii.unhexlify(string) for string in requests.get(url).content.split(b";")]
    return PKCS1_OAEP.new(pvtkey).decrypt(login[0]).decode("ascii") + ";" + PKCS1_OAEP.new(pvtkey).decrypt(login[1]).decode("ascii")


opts = Options()
opts.headless = True
assert opts.headless

login = get_login().split(";")

#browser = webdriver.Firefox(executable_path="./src/geckodriver", options=opts)
browser = webdriver.Firefox(executable_path="./src/geckodriver")
browser.get("https://www.noip.com/")

browser.find_element_by_xpath("/html/body/section[2]/div/ul/li[1]/a").click()
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/input[1]").send_keys(login[0])
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/input[2]").send_keys(login[1])
browser.find_element_by_xpath("/html/body/section[4]/div/div[2]/form/button").click()

input("Press ENTER to continue...")
browser.close()
exit(0)
