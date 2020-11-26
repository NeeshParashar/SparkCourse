/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/
package test
import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

object JsonExample {

  def main(args:Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder().master("local[1]").appName("EmployeeDF").getOrCreate()
    val df = spark.read.json("F:\\Learning\\BigData\\Spark\\sparkexamples.dataset\\employee-data\\emp_data.json")
    df.show()

  }// main
  }
