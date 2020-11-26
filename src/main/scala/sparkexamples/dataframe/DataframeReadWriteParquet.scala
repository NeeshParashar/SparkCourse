/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/
package sparkexamples.dataframe

import org.apache.spark.sql.SparkSession
import org.apache.log4j.{Level, Logger}

object DataframeReadWriteParquet {
  def main(args:Array[String]):Unit= {
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark: SparkSession = SparkSession.builder().master("local[1]").appName("ParquetReadWrite").getOrCreate()

    val data = Seq(("James ","","Smith","36636","M",3000),
                  ("Michael ","Rose","","40288","M",4000),
                  ("Robert ","","Williams","42114","M",4000),
                  ("Maria ","Anne","Jones","39192","F",4000),
                  ("Jen","Mary","Brown","","F",-1))

    val columns = Seq("firstname","middlename","lastname","dob","gender","salary")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns:_*)

    /*  save dataframe to parquet file */
    df.write.parquet("src\\main\\output\\people.parquet")

    /*  read data from parquet file */
    val parqDF = spark.read.parquet("src\\main\\output\\people.parquet")
    parqDF.createOrReplaceTempView("ParquetTable")

    spark.sql("select * from ParquetTable where salary >= 4000").explain()
    val parkSQL = spark.sql("select * from ParquetTable where salary >= 4000 ")

    parkSQL.show()
    parkSQL.printSchema()
    /*  save dataframe with partition*/
    df.write
      .partitionBy("gender","salary")
      .parquet("src\\main\\output\\people2.parquet")

    val parqDF2 = spark.read.parquet("src\\main\\output\\people2.parquet")
    parqDF2.createOrReplaceTempView("ParquetTable2")

    val df3 = spark.sql("select * from ParquetTable2  where gender='M' and salary >= 4000")
    df3.explain()
    df3.printSchema()
    df3.show()

    val parqDF3 = spark.read.parquet("src\\main\\output\\people2.parquet\\gender=M")
    parqDF3.show()

  }
}
