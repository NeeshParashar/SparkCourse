/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/

package sparkexamples.misc

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.util.LongAccumulator

object accumulator {
  def main(args:Array[String]):Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().master("local[1]").appName("LoadEmployee").getOrCreate()
    //empno,ename,designation,manager,hire_date,sal,deptno
    val dspath = "src\\main\\datasets\\empds\\emp_data.csv"
    val rdd = spark.sparkContext.textFile(dspath)

    /* use of non acumulator */
    println("-----count record using local variable----")
    var Count = 0
    val rdd1 = rdd.foreach(x => Count += 1 )
    println(Count)

    /* use of acumulator */
    println("-----count record using accumulator----")
    var countAccu = spark.sparkContext.longAccumulator("count1")

    val rdd2 = rdd.foreach(x => countAccu.add(1))
    println(countAccu.value)

  }
}
