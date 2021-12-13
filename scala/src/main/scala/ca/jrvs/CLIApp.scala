package ca.jrvs

import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession
import ca.jrvs.ca.spark.Executor
import ca.jrvs.spark.sessionMaker

object CLIApp {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: scalaSpark src backup")
    }

    Executor(args[0], args[1])
  }
}