import urllib2
import sys
import json
import torndb
import datetime
#import re

reload(sys)
sys.setdefaultencoding('utf-8')


req4 = urllib2.Request(url4 , headers=headers)
content4 = urllib2.urlopen(req4).read()
data4 = json.loads(content4);

for area in data4["data"]:
    for each in data4["data"][area]:
        temp = {}
        temp["ciname"] = str(each["nm"])
        temp["cinemaId"] = str(each["id"])
        temp["city"] = "广州"
        temp["longitude"] = str(each["lng"])
        temp["latitude"] = str(each["lat"])
        temp["area"] = str(area)
        temp["address"] = str(each["addr"])
        temp["phone"] = str(each[""])
        #url5 = "http://m.maoyan.com/shows/"+temp["id"]+"?_v_=yes"
        #req5 = urllib2.Request(url5 , headers=headers)
        #content5 = urllib2.urlopen(req5).read()
        #reg = r'data-tel="([^\"]*)"'
        #x = re.compile(reg)
        #phone = re.findall(x,content5)
        #print phone
        print temp["address"]
        print temp["area"]
        cinema.append(temp)
