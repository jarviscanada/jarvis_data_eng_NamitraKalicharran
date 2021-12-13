package ca.jrvs.spark

import org.apache.spark.sql.SparkSession

import ca.jrvs.spark.sessionMaker

object Executor {
  // Create SparkSession with SQL Dataframes and Hive configuration
  var spark: SparkSession = sessionMaker.getSparkSession

  def job(spark: SparkSession, src: String, backup: String) = {
    import spark.sql

    val df1 = sql("SELECT * FROM " + src + " USING hive")
    val df2 = sql("SELECT * FROM " + backup + " USING hive")

    val result = df1.exceptAll(df2)

  }

  def main(src: String, backup: String): Unit = {
    // Run Spark manipulations
    job(spark, src, backup)
  }
}
