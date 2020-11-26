/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/

package sparkexamples.rdd

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

object WeatherMonthlyAvg extends App {
  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().appName("Broadcastdf").master("local").getOrCreate()

  val rdd = spark.sparkContext.textFile("src\\main\\datasets\\weather.txt")

  val rdd_temp = rdd.map(x => {
              val a = x.split(",")
              (a(0) + "_" + a(1),a(3).toInt)
      })
  val rdd_m = rdd.map(x => {
    val a = x.split(",")
    (a(0) + "_" + a(1),1)
  })

  val rdd_m_count = rdd_m.reduceByKey(_+_)
  rdd_m_count.foreach(println)

  val rdd3 = rdd_temp.reduceByKey(_+_)
  rdd3.foreach(println)

  //def add(x:String,y:String):Int = (x.toInt + y.toInt)

 }
