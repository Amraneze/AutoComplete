# ContentSquare Auto-complete Challenge

[![codecov](https://codecov.io/gh/Amraneze/AutoComplete/branch/master/graph/badge.svg)](https://codecov.io/gh/Amraneze/AutoComplete)
[![CircleCI](https://circleci.com/gh/Amraneze/AutoComplete.svg?style=svg)](https://circleci.com/gh/Amraneze/AutoComplete)

A challenge to display my knowledge in Scala. 
<br />
The purpose of the challenge is to suggest four words based on a keyword.

>Note: Check the [Test_DE.pdf](docs/Test_DE.pdf) for more detailed information.

## Getting Started

### Prerequisites

#### Sbt

* Memory: At least 128MB
* Operating System: Any operating system.
* Disk Space: At least 124MB (for JRE) of available disk space.

#### Docker

* Memory: At least 2GB
* Operating System: Linux kernel version 3.10 or higher, Windows x Pro/Ent x64.
* Disk Space: 3GB of available disk space (For docker images and builds).

> NOTE: You may find all the details in the following page [Docker System requirements](https://docs.docker.com/datacenter/ucp/1.1/installation/system-requirements/)

### Installing

#### Sbt
**You can follow this link [Install Sbt](https://www.scala-sbt.org/release/docs/Setup.html) to install Sbt**

#### Docker
**You can follow this link [Install Docker](https://docs.docker.com/install/) to install Docker**
>Note: This workshop is tested with Docker Community Edition `17.12.0-ce-win47 (15139), build 9c692cd` on `Win 10 Pro Version 1909 Build 18363.720`.

## Running the project

### With SBT
From the root directory of the project, you can:

#### Compile the project:
```
sbt compile
```

#### Run the Main App:
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

#### Package the project:
```
sbt package
```
>NOTE: sbt package is packaging the project in a JAR file which is a normal Java JAR file.
#### Run the test cases
```
sbt test
```

### With Docker
After installing Docker and you want to run the project you can just run the following command:

>Note: You should be in root of the folder to execute these commands
>
You should build the image
```
docker build -t auto-complete:v0.1 .
```

Then you can run a container with the image built using:

#### providing a file
```
docker run -e "FILE_PATH=docs/Words.txt" -e "KEYWORD=prog" auto-complete:v0.1
```
#### providing a string
```
docker run -e "STRING=project runway,pinterest,river,kayak,progenex,progeria,pg&e,project free tv,bank,proactive,progesterone,press democrat,priceline,pandora,reprobe,paypal" -e "DELIMITER=," -e "KEYWORD=prog" auto-complete:v0.1
```


