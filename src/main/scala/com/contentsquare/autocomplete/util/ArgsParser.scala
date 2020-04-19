package com.contentsquare.autocomplete.util

import com.contentsquare.autocomplete.util.ArgsParser.{ArgMap, usage}

import scala.annotation.tailrec

case class ArgsParser(args: Seq[String]) {

  /**
    * A function that will parse arguments in the App and returns a
    * Map of symbols with their values. If an argument was given
    * it will print the usage and throw an RuntimeException
    * @param argsList the list of arguments that was given
    * @return A map of field with their values
    */
  @throws[RuntimeException]
  def parseArgs(argsList: Seq[String]): ArgMap = {
    @tailrec
    def parseArgs(argsMap: ArgMap, argumentsList: Seq[String]): ArgMap = {
      argumentsList match {
        case Seq()                                    => argsMap
        case Seq("--file", file, tail @ _*)           => parseArgs(argsMap ++ Map(Symbol("file") -> file), tail)
        case Seq("--string", string, tail @ _*)       => parseArgs(argsMap ++ Map(Symbol("string") -> string), tail)
        case Seq("--delimiter", delimiter, tail @ _*) => parseArgs(argsMap ++ Map(Symbol("delimiter") -> delimiter), tail)
        case Seq("--keyword", delimiter, tail @ _*)   => parseArgs(argsMap ++ Map(Symbol("keyword") -> delimiter), tail)
        case option +: _ =>
          println(usage)
          throw new RuntimeException(s"The argument $option is not supported yet.")
      }
    }
    parseArgs(Map.empty, argsList)
  }

  // Maybe to use just an object instead of a case class
  // and move this code to main App. The only purpose of
  // this code is to check whether the args are empty or
  // not so it can display the usage text
  if (args.isEmpty) {
    println(usage)
    throw new RuntimeException("No arguments were provided to the App.")
  }
}

object ArgsParser {
  type ArgMap = Map[Symbol, Any]
  val usage = """Usage: app --file file --keyword keyword Or app --string string --delimiter delimiter --keyword keyword"""
}
