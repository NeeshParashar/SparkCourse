/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/

package sparkexamples.dataframe

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.storage.StorageLevel

object DFcachePersist {
  def main(args:Array[String]):Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().master("local[1]").appName("EmployeeDF").getOrCreate()
    val datafilepath_emp = "src\\main\\datasets\\empds\\emp_data.csv"
    val df_emp = spark.read.format("csv")
                      .option("header", true)
                      .option("inferSchema", true)
                      .load(datafilepath_emp)

    //    import org.apache.spark.storage.StorageLevel
    df_emp.cache()
    df_emp.persist(StorageLevel.MEMORY_AND_DISK)
    df_emp.persist(StorageLevel.DISK_ONLY)

    df_emp.show()

  } // main()
}
