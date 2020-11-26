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
import org.apache.spark.sql.types._
import org.apache.spark.sql.functions.lit
import org.apache.spark.storage.StorageLevel
object sample {

  def main(args:Array[String]) : Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder().master("local[2]").appName("test").getOrCreate()

    // join
    val spath_emp = "F:\\Learning\\BigData\\Spark\\sparkexamples.dataset\\employee-data\\emp_data.csv"
    val df_emp = spark.read.format("csv").option("header",true).load(spath_emp)

    // join
    val spath_dept = "F:\\Learning\\BigData\\Spark\\sparkexamples.dataset\\employee-data\\emp_dept.csv"
    val df_dept = spark.read.format("csv").option("header",true).load(spath_dept)


    //option1
    df_emp.createOrReplaceTempView("emptbl")
    df_dept.createOrReplaceTempView("deptbl")
    val df_join1 = spark.sql("SELECT emp.*, dept.* FROM emptbl emp FULL OUTER JOIN deptbl dept ON emp.deptno=dept.deptno")
    df_join1.show()
  // Option 2

    val df_join2 = df_emp.join(df_dept, df_emp("deptno") ===df_dept("deptno"), "inner")
    df_join2.show()

    val df5 =  df_join2.withColumn("newCol", lit(10))
    df5.show()

    val df6 = df5.withColumnRenamed("newCol","age")
    df6.show()

    val df7 = df6.drop("age")
    df7.show()

    df7.columns.foreach(println)


    df5.cache()

    df5.persist(StorageLevel.MEMORY_AND_DISK)


  }

}
