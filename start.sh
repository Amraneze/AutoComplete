#!/bin/bash

if test -z "$FILE_PATH" ; then
    sbt "run --string \"${STRING}\" --delimiter \"${DELIMITER}\" --keyword \"${KEYWORD}\""
else
    sbt "run --file \"${FILE_PATH}\" --keyword \"${KEYWORD}\""
fi