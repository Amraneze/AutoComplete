package com.contentsquare.autocomplete

import com.contentsquare.autocomplete.job.AutoCompleteJob
import com.contentsquare.autocomplete.util.ArgsParser
import com.contentsquare.autocomplete.util.ArgsParser.ArgMap

object App extends App {

  val argsParser: ArgsParser = ArgsParser(args.toSeq)
  val parsedArgs: ArgMap     = argsParser.parseArgs(args.toSeq)

  val autoCompleteResult: Seq[String] = AutoCompleteJob.autoComplete(parsedArgs)
  autoCompleteResult.foreach(println)

}
