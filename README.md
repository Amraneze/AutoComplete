# ContentSquare Auto-complete Challenge

A challenge to display my knowledge in Scala. 
<br />
The purpose of the challenge is to suggest four words based on a keyword.

>Note: Check the [Test_DE.pdf](docs/Test_DE.pdf) for more detailed information.

## Getting Started

### Prerequisites

* Memory: At least 128MB
* Operating System: Any operating system.
* Disk Space: At least 124MB (for JRE) of available disk space.

### Installing

**You can follow this link [Install Sbt](https://www.scala-sbt.org/release/docs/Setup.html) to install Sbt**

## Running the project

From the root directory of the project, you can:

### Compile the project:
```
sbt compile
```

### Run the Main App:
```
sbt run --string "project runway,pinterest,river,kayak,progenex,progeria,pg&e,project,free,tv,bank,proactive,progesterone,press,democrat,priceline,pandora,reprobe,paypal" --delimiter "," --keyword "pro"
```
>Note: If you use sbt run without argument you will have an exception thrown with an example of the command that needs to be run with the App.

For example you can try:
```
sbt run --string "project runway,pinterest,river,kayak,progenex,progeria,pg&e,project,free,tv,bank,proactive,progesterone,press,democrat,priceline,pandora,reprobe,paypal" --delimiter "," --keyword "pro"
```
And you will get this result displayed in your terminal
```
proactive
progenex
progeria
progesterone
```

### Package the project:
```
sbt package
```
>NOTE: sbt package is packaging the project in a JAR file which is a normal Java JAR file in target/${scalaVersion}/autocomplete_${scalaVersion}-${appVersion}.jar.
### Run the test cases
```
sbt test
```