package com.contentsquare.autocomplete.job

import com.contentsquare.autocomplete.util.ArgsParser.ArgMap
import com.contentsquare.autocomplete.util.CommonUtils

object AutoCompleteJob {

  /**
    * A function that takes arguments with symbol and parse them to
    * have a list of words that will be sorted and then it would take
    * 4 words that starts with the given keyword
    * @param args, the arguments given by the user
    * @return the list of words of at max 4 words that starts with the keyword
    */
  def autoComplete(args: ArgMap): Seq[String] = {
    val file: Option[String] =
      args.get(Symbol("file")).asInstanceOf[Option[String]].filter(!_.isEmpty)
    val string: Option[String] =
      args.get(Symbol("string")).asInstanceOf[Option[String]].filter(!_.isEmpty)
    val delimiter: String = args
      .get(Symbol("delimiter"))
      .asInstanceOf[Option[String]]
      .getOrElse("\n")
    val keyword: String = args
      .get(Symbol("keyword"))
      .asInstanceOf[Option[String]]
      .getOrElse("")
    val parsedStrings: Seq[String] = (file, string) match {
      case (None, Some(str))      => parseString(str, delimiter)
      case (Some(filePath), None) => parseFile(filePath)
      // We won't have this case because it's handled in main
      // App as we go through all arguments but we don't know
      case _ => throw new RuntimeException("No data was given to this App :(")
    }
    parsedStrings.filter(_.startsWith(keyword)).sorted.take(4)
  }

  /**
    * A function that read a file
    * @param file, file path to be read
    * @return List of strings that was read from the file
    */
  private def parseFile(file: String): Seq[String] = CommonUtils.readFile(file)

  /**
    * A function that split a string using a delimiter
    * @param string the string that contains list of words joined by a delimiter
    * @param delimiter, the delimiter that would be used for splitting the sentence
    * @return a list of splitted words from the sentence
    */
  private def parseString(string: String, delimiter: String): Seq[String] =
    CommonUtils.parseString(string, delimiter)

}
