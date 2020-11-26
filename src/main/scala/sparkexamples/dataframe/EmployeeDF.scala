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
import util.Property

object EmployeeDF {
  def main(args:Array[String]):Unit ={

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().master("local[1]").appName("EmployeeDF").getOrCreate()

    /* Use of Java Property file - reading dataset file path from property file (application.properties) */
    val prop: Property = new Property
    val emp_dataset_path = prop.getEmpDatasetPath()

    val df = spark.read.csv(emp_dataset_path)
    //df.show()
    val df1 = spark.read.format("csv").option("header",true).option("inferSchema",true)
                  .load(emp_dataset_path)

    df1.show()
    df1.createOrReplaceTempView("employeeTable")
    // val df2 = spark.sql("select empno, ename from employee where ename=\"SMITH\"")  \\using escaped
    val df_temp = spark.sql("select *, sal*2 as doubleSalary from employeeTable where empno >=7900")
    df_temp.show()

  } // end of main
}
