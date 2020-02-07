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

target_id = "PDI_answer48537035"
vote_id = "pd-vote-button10496613"

name1_xpath = "/html/body/div[1]/div[2]/article/div[3]/div[5]/div/div/div/div/div/div/div/div/div[2]/div[1]/label/span[1]"
percentage1_xpath = "/html/body/div[1]/div[2]/article/div[3]/div[5]/div/div/div/div/div/div/div/div/div[2]/div[1]/label/span[2]/span[1]"
name2_xpath = "/html/body/div[1]/div[2]/article/div[3]/div[5]/div/div/div/div/div/div/div/div/div[2]/div[2]/label/span[1]"
percentage2_xpath = "/html/body/div[1]/div[2]/article/div[3]/div[5]/div/div/div/div/div/div/div/div/div[2]/div[2]/label/span[2]/span[1]"


embeded_poll = "https://poll.fm/10496613"  # Do not use, has been blocked.
og_site = "https://www.shreveporttimes.com/story/sports/2020/01/26/12-student-athletes-highlight-21st-times-athlete-week-ballot/4560859002/"

opts = Options()
opts.headless = True
assert opts.headless

browser = webdriver.Firefox(options=opts)
#browser = webdriver.Firefox()
browser.get(og_site)

votes = 0
while(True):
    try:
        browser.get(og_site)

        #WebDriverWait(browser,16).until(EC.presence_of_element_located((By.XPATH,target_id)))
        element = browser.find_element_by_id(target_id)
        browser.execute_script("return arguments[0].scrollIntoView();", browser.find_element_by_xpath("/html/body/div[1]/div[2]/article/div[3]/div[5]/div/div/div/div/div/div/div/div/div/div[1]/div/div/div"))
        element.click()


        #WebDriverWait(browser,10).until(EC.presence_of_element_located((By.XPATH, vote_id)))
        element = browser.find_element_by_id(vote_id)
        element.click()
        time.sleep(4)

        #WebDriverWait(browser,10).until(EC.presence_of_element_located((By.XPATH,percentage_xpath)))
        name1 = browser.find_element_by_xpath(name1_xpath).text
        percentage1 = browser.find_element_by_xpath(percentage1_xpath).text

        name2 = browser.find_element_by_xpath(name2_xpath).text
        percentage2 = browser.find_element_by_xpath(percentage2_xpath).text

        votes+=1
        
        os.sys.stdout.write("Votes: {}  1st: {} @ {}    2nd: {} @ {}".format(votes,name1,percentage1,name2,percentage2))
        os.sys.stdout.flush()
        os.sys.stdout.write("\b" * (7 + len(str(votes)) + len("  1st:  @      2nd:  @  ") + len(percentage1) + len(percentage2) + len(name1) + len(name2)))
    except:
        print("\nClosing Firefox and exiting...")
        browser.close()
        exit(0)