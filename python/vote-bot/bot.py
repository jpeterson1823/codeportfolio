#!/usr/bin/env python3
# Original Site:        https://www.shreveporttimes.com/story/sports/2020/03/01/12-athletes-featured-26th-athlete-week-ballot/4907049002/
#
# Exit Codes:
#   1 = failed to find voting option
#   2 = failed to find vote button

from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import os, platform, sys, time

if "Windows" in platform.platform() : print("Platform: Windows OS")
elif "Mac" in platform.platform() : print("Platform: Mac OS")
else : print("Platform: Linux")
print("VoteBot v1.6 for Cole Joyner.\nThis program will vote infinitely.\nPress CTRL + C to stop/exit program.")

see = False

if len(sys.argv) == 2:
    if sys.argv[1] == "-gui": see = True


target_id = "PDI_answer48645173"
vote_id = "pd-vote-button10513397"


og_site = "https://www.shreveporttimes.com/story/sports/2020/03/01/12-athletes-featured-26th-athlete-week-ballot/4907049002/"

opts = Options()
opts.headless = True
assert opts.headless

caps = DesiredCapabilities().FIREFOX
caps["pageLoadStrategy"] = "normal"

if see:
    browser = webdriver.Firefox(capabilities=caps,executable_path="./geckodriver")
else:
    browser = webdriver.Firefox(capabilities=caps,executable_path="./geckodriver",options=opts)

#browser.maximize_window()
browser.get(og_site)

votes = 0
while(True):
    try:
        try: 
            browser.get(og_site)
            #WebDriverWait(browser,16).until(EC.presence_of_element_located((By.XPATH,target_id)))
            element = browser.find_element_by_id(target_id)
            browser.execute_script("return arguments[0].scrollIntoView();", browser.find_element_by_xpath("/html/body/div[1]/div[2]/article/div[3]/p[6]/span/em/strong"))
            time.sleep(1)
            element.click()
            #WebDriverWait(browser,10).until(EC.presence_of_element_located((By.XPATH, vote_id)))
            element = browser.find_element_by_id(vote_id)
            element.click()
            time.sleep(1)
            votes+=1
            os.sys.stdout.write("\b"*50+"Votes: {}".format(votes))
            os.sys.stdout.flush()
        except:
            print("\b"*50+"Bot failed to vote, retrying...")
    except KeyboardInterrupt:
        browser.close()
        sys.exit(2)