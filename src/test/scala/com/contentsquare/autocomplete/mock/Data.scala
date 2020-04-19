package com.contentsquare.autocomplete.mock

object Data {
  val stringWithBreakLine: String = "project runway\npinterest\nriver\nkayak\nprogenex\nprogeria\npg&e\nproject free tv\nbank\nproactive\nprogesterone\npress democrat\npriceline\npandora\nreprobe\npaypal"
  val stringWithComma: String     = "project runway,pinterest,river,kayak,progenex,progeria,pg&e,project free tv,bank,proactive,progesterone,press democrat,priceline,pandora,reprobe,paypal"
  val file: String                = "src/test/resources/Words.txt"
  val delimiter: String           = "\n"

  val keywordP: String    = "p"
  val keywordPr: String   = "pr"
  val keywordPro: String  = "pro"
  val keywordProg: String = "prog"

  val argsWithFile: Seq[String]                             = Seq("--file", file)
  def argsWithFileWithKeyWord(keyword: String): Seq[String] = Seq("--file", file, "--keyword", keyword)

  val argsWithString: Seq[String]                             = Seq("--string", stringWithBreakLine, "--delimiter", delimiter)
  def argsWithStringWithKeyword(keyword: String): Seq[String] = Seq("--string", stringWithBreakLine, "--delimiter", delimiter, "--keyword", keyword)
}
