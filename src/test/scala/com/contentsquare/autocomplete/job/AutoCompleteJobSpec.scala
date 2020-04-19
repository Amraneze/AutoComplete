package com.contentsquare.autocomplete.job

import java.io.FileNotFoundException

import com.contentsquare.autocomplete.mock.Data._
import com.contentsquare.autocomplete.util.ArgsParser.ArgMap
import com.contentsquare.autocomplete.job.AutoCompleteJobSpec._
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class AutoCompleteJobSpec extends AnyFlatSpec with Matchers {

  it should "return the four words that starts with p and sorted alphabetically" in {
    val resultString: Seq[String] = AutoCompleteJob.autoComplete(stringArgs(keywordP))
    resultString should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordP))

    val resultFile: Seq[String] = AutoCompleteJob.autoComplete(fileArgs(keywordP))
    resultFile should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordP))
  }

  it should "return the four words that starts with pr and sorted alphabetically" in {
    val resultString: Seq[String] = AutoCompleteJob.autoComplete(stringArgs(keywordPr))
    resultString should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordPr))

    val resultFile: Seq[String] = AutoCompleteJob.autoComplete(fileArgs(keywordPr))
    resultFile should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordPr))
  }

  it should "return the four words that starts with pro and sorted alphabetically" in {
    val resultString: Seq[String] = AutoCompleteJob.autoComplete(stringArgs(keywordPro))
    resultString should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordPro))

    val resultFile: Seq[String] = AutoCompleteJob.autoComplete(fileArgs(keywordPro))
    resultFile should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordPro))
  }

  it should "return the four words that starts with prog and sorted alphabetically" in {
    val resultString: Seq[String] = AutoCompleteJob.autoComplete(stringArgs(keywordProg))
    resultString should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordProg))

    val resultFile: Seq[String] = AutoCompleteJob.autoComplete(fileArgs(keywordProg))
    resultFile should contain theSameElementsAs expectedResultForEachKeyword(Symbol(keywordProg))
  }

  it should "return the four sorted words of the list for an empty keyword given" in {
    val expectedResult: Seq[String] = words.sorted.take(4)

    val resultString: Seq[String] = AutoCompleteJob.autoComplete(stringArgs(""))
    resultString should contain theSameElementsAs expectedResult

    val resultFile: Seq[String] = AutoCompleteJob.autoComplete(fileArgs(""))
    resultFile should contain theSameElementsAs expectedResult
  }

  it should "throw a runtime exception if no data was given to the App" in {
    the[RuntimeException] thrownBy {
      AutoCompleteJob.autoComplete(emptyFileArgs(keywordProg))
    } should have message "No data was given to this App :("

    the[RuntimeException] thrownBy {
      AutoCompleteJob.autoComplete(emptyStringArgs(keywordProg))
    } should have message "No data was given to this App :("
  }

  it should "throw a file not found exception if the file does not exist" in {
    the[FileNotFoundException] thrownBy {
      AutoCompleteJob.autoComplete(notExistingFileArgs(keywordProg))
    } should have message s"${nonExistingFile.replace("/", "\\")} (The system cannot find the file specified)"
    // We also use this syntax for catching an exception without checking the message but
    // better to make sure that the text which was thrown is the same as the expected one
    assertThrows[FileNotFoundException] {
      AutoCompleteJob.autoComplete(notExistingFileArgs(keywordProg))
    }
  }

  it should "handle big files" in {
    val result: Seq[String] = AutoCompleteJob.autoComplete(bigFileArgs(keywordProg))
    // We have issue of duplicates, we should find only distinct values to display
    // we have two duplicates "progenitors" and "progeny" in the result
    result should contain theSameElementsAs expectedResultForBigFile
  }
}

object AutoCompleteJobSpec {
  def notExistingFileArgs(keyword: String): ArgMap = Map(Symbol("file") -> nonExistingFile, Symbol("keyword") -> keyword)
  def bigFileArgs(keyword: String): ArgMap         = Map(Symbol("file") -> bigFile, Symbol("keyword")         -> keyword)

  def emptyFileArgs(keyword: String): ArgMap   = Map(Symbol("file") -> "", Symbol("keyword") -> keyword)
  def emptyStringArgs(keyword: String): ArgMap = Map(Symbol("file") -> "", Symbol("keyword") -> keyword)

  def fileArgs(keyword: String): ArgMap   = Map(Symbol("file")   -> file, Symbol("keyword")                  -> keyword)
  def stringArgs(keyword: String): ArgMap = Map(Symbol("string") -> stringWithBreakLine, Symbol("delimiter") -> delimiter, Symbol("keyword") -> keyword)

  val expectedResultForP: Seq[String]       = Seq("pandora", "paypal", "pg&e", "pinterest")
  val expectedResultForPR: Seq[String]      = Seq("press democrat", "priceline", "proactive", "progenex")
  val expectedResultForPRO: Seq[String]     = Seq("proactive", "progenex", "progeria", "progesterone")
  val expectedResultForPROG: Seq[String]    = Seq("progenex", "progeria", "progesterone")
  val expectedResultForBigFile: Seq[String] = Seq("progenitors", "progenitors", "progeny", "progeny")

  val expectedResultForEachKeyword: Map[Symbol, Seq[String]] = Map(Symbol("p") -> expectedResultForP, Symbol("pr") -> expectedResultForPR, Symbol("pro") -> expectedResultForPRO, Symbol("prog") -> expectedResultForPROG)
}
