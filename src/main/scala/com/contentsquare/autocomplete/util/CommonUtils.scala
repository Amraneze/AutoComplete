package com.contentsquare.autocomplete.util

import java.io.IOException
import scala.io.Source

object CommonUtils {

  /**
    * Read a file line by line and returns a list of words in it.
    * @param file, the file path to be read
    * @throws IOException, if the file is not found or other exceptions
    * @return a sequence of words
    */
  @throws[IOException]
  def readFile(file: String): Seq[String] = {
    // In Scala 2.13 we can use just this source.getLines().filter(!_.isBlank).toSeq
    // and we can close the stream after it without having the exception "Stream closed"
    // I guess in Scala with version below 2.13, they are using lazy val so that's why
    // when it's starting to get the data, the stream close itself.
    var words: Seq[String] = Seq.empty
    def readingFile[A <: Source](source: A)(f:A => Seq[String]): Seq[String] = try f(source) finally source.close
    readingFile(Source.fromFile(file)) {
      source => source.getLines().filter(!_.isBlank).foreach{
        line => words = words :+ line
      }
      words
    }
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
