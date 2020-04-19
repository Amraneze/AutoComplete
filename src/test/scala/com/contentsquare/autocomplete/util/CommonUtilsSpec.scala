package com.contentsquare.autocomplete.util

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import com.contentsquare.autocomplete.mock.Data._

class CommonUtilsSpec extends AnyFlatSpec with Matchers {

  it should "parse a string with break line delimiter and returns a list of string" in {
    val parsedStrings = CommonUtils.parseString(stringWithBreakLine, "\n")
    parsedStrings should contain theSameElementsAs words
  }

  it should "parse a string with comma delimiter and returns a list of string" in {
    val parsedStrings = CommonUtils.parseString(stringWithComma, ",")
    parsedStrings should contain theSameElementsAs words
  }

  it should "read a file and returns a list of string" in {
    val parsedStrings = CommonUtils.readFile(file)
    parsedStrings should contain theSameElementsAs words
  }

}
