/****************************************************************
'*   Author:  Neesh Parashar (Big Data Architect and Trainer)   *
'*   Contact for big data training:							                *
'*   Phone +91 7676451682(Neesh) | +91 9110257247(KC)           *
'*   email@ datalogicconsultancy@gmail.com                      *
'*   Copyright © 2018-2020, Datalogic. All rights reserved.     *
'*   Use only for learning purpose                              *
'***************************************************************/

package scalaexamples

object HigherOrder {

  def main(args:Array[String]) : Unit = {

    def getMax(x:Int,y:Int) : Int = if (x>y) x else y

    def getMaxOfThreeValues(X:Int,Y:Int, Z:Int, f:(Int,Int)=> Int) :Int = {
        val max_of_x_y = f(X,Y)
        val max_of_x_y_z = f(max_of_x_y,Z)
        max_of_x_y_z
    }
    val maxVal = getMaxOfThreeValues(50,20,30,getMax)
    println(maxVal)
  } // main

}
