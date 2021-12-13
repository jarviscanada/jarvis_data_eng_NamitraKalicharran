package ca.jrvs.spark

import org.apache.spark.sql.SparkSession

import ca.jrvs.spark.sessionMaker

object Executor {
  // Create SparkSession with SQL Dataframes and Hive configuration
  var spark: SparkSession = _

  def main(): Unit = {
    spark = sessionMaker.getSparkSession
  }

  def job(spark: SparkSession, src: String, backup: String) = {
    import spark.sql

    val df1 = sql("SELECT * FROM " + src + " USING hive")
    val df2 = sql("SELECT * FROM " + backup + " USING hive")

    // Get rows in backup that aren't in src
    val unarchived = df2.except(df1)

    // update backup to have the only the rows in src
    val df2 = df1.union(df2).except(unarchived)

  }

  def main(src: String, backup: String): Unit = {
    // Run Spark manipulations
    job(spark, src, backup)
  }
}
