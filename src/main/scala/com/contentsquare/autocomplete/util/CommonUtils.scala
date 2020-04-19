package com.contentsquare.autocomplete.util

import java.io.IOException
import scala.io.{BufferedSource, Source}

object CommonUtils {

  /**
    * Read a file line by line and returns a list of words in it.
    * @param file, the file path to be read
    * @throws IOException, if the file is not found or other exceptions
    * @return a sequence of words
    */
  @throws[IOException]
  def readFile(file: String): Seq[String] = {
    val enumSource: BufferedSource = Source.fromFile(file)
    val data                       = enumSource.getLines.filter(!_.isBlank).toSeq
    enumSource.close
    data
  }

  /**
    * A function that split a string to a list of strings, be careful to not use space
    * as delimiter in case that the string contains words that has space in them.
    * @param string, the string to be spillited
    * @param delimiter, the delimiter to be used for the split
    * @return a sequence of words of the string
    */
  def parseString(string: String, delimiter: String): Seq[String] =
    string.split(delimiter).toSeq

}
