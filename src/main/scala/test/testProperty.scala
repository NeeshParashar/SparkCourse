/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/
package test
import org.apache.log4j.{Level, Logger}
import util.Property

object testProperty {

  def main(args:Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    var prop: Property = new Property
    //p.createProperties()
    //p.loadProperty()
    println(prop.getEmpDatasetPath())

  } // main ()

}
