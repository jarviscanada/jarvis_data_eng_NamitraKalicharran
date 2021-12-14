package ca.jrvs

import ca.jrvs.spark.Executor

object CLIApp {
  def main(args: Array[String]): Unit = {
    if (args.length != 2) {
      throw new IllegalArgumentException("USAGE: scalaSpark src backup")
    }
    val srcFile = args(0)
    val backupFile = args(1)

    Executor(srcFile, backupFile)
  }
}