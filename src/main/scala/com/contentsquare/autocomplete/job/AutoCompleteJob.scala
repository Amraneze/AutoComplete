package com.contentsquare.autocomplete.job

import com.contentsquare.autocomplete.util.ArgsParser.ArgMap
import com.contentsquare.autocomplete.util.CommonUtils

object AutoCompleteJob {
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

  private def parseFile(file: String): Seq[String] = CommonUtils.readFile(file)

  private def parseString(string: String, delimiter: String): Seq[String] =
    CommonUtils.parseString(string, delimiter)

}
