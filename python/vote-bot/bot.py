#!/usr/bin/env python3
# Original Site:        https://www.shreveporttimes.com/story/sports/2020/01/26/12-student-athletes-highlight-21st-times-athlete-week-ballot/4560859002/
# Target Element:       id="PDI_answer48537035"
# Embeded poll:         https://poll.fm/10496613
#
# Exit Codes:
#   1 = failed to find voting option
#   2 = failed to find vote button

from selenium import webdriver
from selenium.webdriver.firefox.options import Options
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import os, platform, sys, time

if "Windows" in platform.platform() : print("Platform: Windows OS")
else : os.system("Platform: Mac OS")
print("VoteBot v1.5 for Bailee Creasey.\nThis program will vote infinitely.\nPress CTRL + C to stop/exit program.")

target_id = "PDI_answer48581328"
vote_id = "pd-vote-button10503431"


#embeded_poll = "https://poll.fm/10496613"  # Do not use, has been blocked.
og_site = "https://www.shreveporttimes.com/story/sports/2020/01/26/12-student-athletes-highlight-21st-times-athlete-week-ballot/4560859002/"

opts = Options()
opts.headless = True
assert opts.headless

#browser = webdriver.Firefox(options=opts)
browser = webdriver.Firefox()
browser.get(og_site)

votes = 0
while(True):
    try:
        browser.get(og_site)

        #WebDriverWait(browser,16).until(EC.presence_of_element_located((By.XPATH,target_id)))
        element = browser.find_element_by_id(target_id)
        browser.execute_script("return arguments[0].scrollIntoView();", browser.find_element_by_id("pd-vote-button10503431"))
        element.click()


        #WebDriverWait(browser,10).until(EC.presence_of_element_located((By.XPATH, vote_id)))
        element = browser.find_element_by_id(vote_id)
        element.click()
        time.sleep(4)

        #WebDriverWait(browser,10).until(EC.presence_of_element_located((By.XPATH,percentage_xpath)))
        name1 = browser.find_element_by_xpath(name1_xpath).text

        votes+=1
        
        os.sys.stdout.write("Votes: {}".format(votes))
        os.sys.stdout.flush()
    except:
        print("\nClosing Firefox and exiting...")
        browser.close()
        exit(0)