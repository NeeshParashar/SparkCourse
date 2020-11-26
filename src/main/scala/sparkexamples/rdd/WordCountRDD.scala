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

object WordCountRDD extends App {

  Logger.getLogger("org").setLevel(Level.ERROR)
  val spark = SparkSession.builder().appName("Broadcastdf").master("local").getOrCreate()

  val rdd_lines = spark.sparkContext.textFile("src\\main\\datasets\\wordcount.txt")
  rdd_lines.foreach(println)
  val rdd_words = rdd_lines.flatMap( w => w.split(" "))
  val rdd_kv = rdd_words.map(w => (w,1))
  val wordcount = rdd_kv.reduceByKey(_+_)
  println("-- word count output ---")
  wordcount.foreach(println)

}
