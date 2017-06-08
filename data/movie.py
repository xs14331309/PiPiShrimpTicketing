#!/usr/bin/python
#-*- coding: UTF-8 -*-

import urllib2
import sys
import json
import torndb
import datetime
import re

reload(sys)
sys.setdefaultencoding('utf-8')

mydb = {
  'host':'localhost',
  'database':'filmTicketing',
  'user':'root',
  'password':'root',
  'time_zone':'+8:00'
}
'''
db = torndb.Connection(host=mydb['host'],
                      database=mydb['database'],
                      user = mydb['user'],
                      password=mydb['password'],
                      time_zone=mydb['time_zone'],
                      charset="utf8")
'''
url1 = "http://m.maoyan.com/movie/list.json?type=hot&offset=0&limit=1000"
url3 = "http://m.maoyan.com/movie/list.json?type=coming&offset=0&limit=1000"
url4 = "http://m.maoyan.com/cinemas.json"
headers = {'User-Agent':'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6'}

req1 = urllib2.Request(url1 , headers=headers)
content1 = urllib2.urlopen(req1).read()

req2 = urllib2.Request(url3 , headers=headers)
content2 = urllib2.urlopen(req2).read()


data1 = json.loads(content1)
data2 = json.loads(content2)
data = data1["data"]["movies"] + data2["data"]["movies"]

res = {}
movie = []
cinema = []

i = 0

#nm , img, 3d, star, dir, id, rt, sc,dur(min)

for each in data:
    i = i+1
    temp = {}
    temp["moviename"] = str(each["nm"])
    temp["imgUrl"] = str(each["img"])
    temp["actor"] = str(each["star"])
    temp["director"] = str(each["dir"])
    temp["score"] = each["sc"]/2
    print temp["score"]
    temp["type"] = str(each["cat"]);
    if (len(each["rt"]) < 10):
        if (len(each["showInfo"]) < 10):
            continue
        else:
            temp["relaseTime"] = str(each["showInfo"][0:10])
    else:

        temp["relaseTime"] = str(each["rt"][0:10])
    url2 = "http://m.maoyan.com/movie/"+str(each["id"])+".json"
    req2 = urllib2.Request(url2 , headers=headers)
    text = urllib2.urlopen(req2).read()
    temp["movieId"] = each["id"]
    text = json.loads(text)
    temp["brief"] = str(text["data"]["MovieDetailModel"]["dra"][3:-4])
    temp["length"] = str(text["data"]["MovieDetailModel"]["dur"])
    try:
        date_time = datetime.datetime.strptime(temp["relaseTime"],"%Y-%m-%d")
        temp["shelfTime"] = (date_time + datetime.timedelta(days = 8)).strftime('%Y-%m-%d')
    except:
        temp["shelfTime"] = ""
        continue
    movie.append(temp)

    print "insert into MOVIE values({0}, \'{1}\', \'{2}\', NULL, \'{3}\', \'{4}\', \'{5}\', {6}, {7}, \'{8}\', \'{9}\', \'{10}\')".format(
                temp["id"], temp["name"], temp["img"], temp["dir"], temp["star"],
                temp["brief"], temp["score"], temp["length"],temp["data"], temp["data_end"], temp["cat"])
'''
    db.execute("insert into MOVIE values({0}, \'{1}\', \'{2}\', NULL ,\'{3}\', \'{4}\', \'{5}\', {6}, {7}, \'{8}\', \'{9}\', \'{10}\')".format(
                temp["id"], temp["name"], temp["img"], temp["dir"], temp["star"],
                temp["brief"], temp["score"], temp["length"],temp["data"], temp["data"], temp["star"], temp["cat"]))
'''


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

res["movie"] = movie
res["cinema"] = cinema
#res = json.dumps(res, ensure_ascii=False)
#with open('data.json', 'w') as json_file:
#    json_file.write(res)
