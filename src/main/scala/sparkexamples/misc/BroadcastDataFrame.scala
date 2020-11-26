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

object BroadcastDataFrame extends App{
    Logger.getLogger("org").setLevel(Level.ERROR)
    val spark = SparkSession.builder().appName("Broadcastdf").master("local").getOrCreate()



    val data = Seq(("James","Smith","USA","CA"),
                    ("Michael","Rose","USA","NY"),
                    ("Robert","Williams","USA","CA"),
                    ("Maria","Jones","USA","FL")
                  )

    val columns = Seq("firstname","lastname","country","state")
    import spark.sqlContext.implicits._
    val df = data.toDF(columns:_*)
    df.show(false)

    /* Use of broadcast variable */
    val states = Map(("NY","New York"),("CA","California"),("FL","Florida"))
    val countries = Map(("USA","United States of America"),("IN","India"))

    val broadcastStates = spark.sparkContext.broadcast(states)
    val broadcastCountries = spark.sparkContext.broadcast(countries)

    val df1 = df.map(row=>{
              val country = row.getString(2)
              val state = row.getString(3)
              val fullCountry = broadcastCountries.value.get(country).get
              val fullState = broadcastStates.value.get(state).get
              (row.getString(0),row.getString(1),fullCountry,fullState)
            }).toDF(columns:_*)

    df1.show(false)

}