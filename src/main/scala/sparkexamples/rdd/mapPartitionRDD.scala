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

object mapPartitionRDD {

  def main(args:Array[String]):Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)
    // System.setProperty("hadoop.home.dir","c:\\Users\\BigData\\hadoop")
    val spark = SparkSession.builder().master("local[1]").appName("LoadEmployee").getOrCreate()

    val num_rdd = spark.sparkContext.parallelize(Seq('A','B','C','D','E','F','G','H'),3)

    /*example map(0 */
    println("--- Example map() ------")
    num_rdd.map(x => {
              println("Inside map function")
              (x, "Hello")
        }).collect().foreach(println)

    /*example mapPartitions() */
    println("--- Example mapPartitions() ------")
    num_rdd.mapPartitions(iter => {
              println("inside mapPartition")
              (List(iter.next).iterator)
          }).collect().foreach(println)

    /*example mapPartitionWithIndex() */
    println("--- Example mapPartitionWithIndex ------")
    val color_rdd = spark.sparkContext.parallelize(List("red","yellow","green","blue","white","black"),3)

    val rdd_c = color_rdd.mapPartitionsWithIndex((index, iter) => {
                  println("inside mapPartitionsWithIndex index:" + index + " values:" + iter.toList)
                  val myList = iter.toList
                  myList.map(x=> (x,1) ).iterator
            })
    rdd_c.foreach(println)

  } //main
}
