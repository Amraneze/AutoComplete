package com.contentsquare.autocomplete.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.contentsquare.autocomplete.mock.Data._
import com.contentsquare.autocomplete.util.ArgsParser.ArgMap

class ArgsParserSpec extends AnyFlatSpec with Matchers {

  it should "throw a runtime exception if no arguments was provided" in {
    the[RuntimeException] thrownBy {
      ArgsParser(Seq.empty)
    } should have message "No arguments was given to the App."
  }

  it should "throw a runtime exception if an arguments not supported was provided" in {
    val argsParser = ArgsParser(argsWithFile ++ Seq("--test"))
    the[RuntimeException] thrownBy {
      argsParser.parseArgs(argsWithFile ++ Seq("test"))
    } should have message "The argument test is not supported yet."
  }

  it should "parse arguments with file option" in {
    val argsParser: ArgsParser = ArgsParser(argsWithFile)
    val parsedArgs: ArgMap     = argsParser.parseArgs(argsWithFile)
    parsedArgs(Symbol("file")) should equal(file)
  }

  it should "parse arguments with file option and a p as a keyword" in {
    val argsParser: ArgsParser = ArgsParser(argsWithFileWithKeyWord(keywordP))
    val parsedArgs: ArgMap     = argsParser.parseArgs(argsWithFileWithKeyWord(keywordP))
    parsedArgs(Symbol("file")) should equal(file)
    parsedArgs(Symbol("keyword")) should equal(keywordP)
  }

  it should "parse arguments with string option" in {
    val argsParser: ArgsParser = ArgsParser(argsWithString)
    val parsedArgs: ArgMap     = argsParser.parseArgs(argsWithString)
    parsedArgs(Symbol("string")) should equal(stringWithBreakLine)
    parsedArgs(Symbol("delimiter")) should equal("\n")
  }

  it should "parse arguments with string option and a p as a keyword" in {
    val argsParser: ArgsParser = ArgsParser(argsWithStringWithKeyword(keywordP))
    val parsedArgs: ArgMap     = argsParser.parseArgs(argsWithStringWithKeyword(keywordP))
    parsedArgs(Symbol("string")) should equal(stringWithBreakLine)
    parsedArgs(Symbol("delimiter")) should equal("\n")
    parsedArgs(Symbol("keyword")) should equal(keywordP)
  }

}
