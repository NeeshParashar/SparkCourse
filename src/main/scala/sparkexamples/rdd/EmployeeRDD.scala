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

object EmployeeRDD {
  def main(args:Array[String]):Unit ={

    Logger.getLogger("org").setLevel(Level.ERROR)
   // System.setProperty("hadoop.home.dir","c:\\Users\\BigData\\hadoop")
    val spark = SparkSession.builder().master("local[1]").appName("LoadEmployee").getOrCreate()
    //empno,ename,designation,manager,hire_date,sal,deptno
    val dspath = "src\\main\\datasets\\empds\\emp_data.csv"
    val rdd = spark.sparkContext.textFile(dspath)

    val rdd_f = rdd.filter(x => {
                          val a = x.split(",")
                          if (a(0)=="empno") false else true
                        })

    val rdd_tuple = rdd_f.map(x => {
                        val a = x.split(",")
                        (a(0),a(5))  // empno, sal
                      })

    val rdd_doubleSal = rdd_tuple.map(x => (x._1, x._2, x._2.toInt *2))
    val rdd_asRecord = rdd_doubleSal.map(x => x._1 + "," + x._2 + "," + x._3)

    println("--- double salary for each employee----")
    println("empno,sal,doubleSal")
    rdd_asRecord.foreach(println)
    rdd_asRecord.saveAsTextFile("src\\main\\output\\emp_data_output1.csv")
  } // end of main
}
