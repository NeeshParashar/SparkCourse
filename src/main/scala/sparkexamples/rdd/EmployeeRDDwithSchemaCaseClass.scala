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

object EmployeeRDDwithSchemaCaseClass {
  def main(args:Array[String]) : Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark = SparkSession.builder().master("local[1]").getOrCreate()
    val filePath = "F:\\Learning\\BigData\\Spark\\sparkexamples.dataset\\employee-data\\emp_data.csv"

    val rdd = spark.sparkContext.textFile(filePath)
    val rdd_f = rdd.filter(line => !line.startsWith("emp"))
    val rdd_fields  = rdd_f.map( x => x.split(","))

    //val rdd1 = rdd_fields.map(x => (x(0),x(1),x(2),x(3),x(4),x(5),x(6)))
    //rdd1.foreach(println)

    // put a cursor on rdd1 then press alt = to see the data type of rdd1
    val rdd1 = rdd_fields.map(e => empcaseclass(e(0).toInt,e(1),e(2),e(3).toInt,e(4) ,e(5).toInt,e(6).toInt))
    // print sparkexamples.dataframe.rdd having each element as empcaseclass type
//    val rdd2 = rdd1.map(x => (x.ename,x.empno,x.sal))
//    rdd1.foreach(println)

    import spark.implicits._
    val df = rdd1.toDF

    df.show()
    df.printSchema()

  }
  //|empno| ename|designation|manager| hire_date| sal|deptno|
  case class empcaseclass(empno:Int,ename:String, designation:String,manager:Int,hite_date:String,sal:Int,deptno:Int)

}
