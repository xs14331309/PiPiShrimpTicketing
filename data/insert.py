#!/usr/bin/python
#-*- coding: UTF-8 -*-
import torndb

reload(sys)
sys.setdefaultencoding('utf-8')

mydb = {
  'host':'localhost',
  'database':'filmTicketing',
  'user':'root',
  'password':'root',
  'time_zone':'+8:00'
}
