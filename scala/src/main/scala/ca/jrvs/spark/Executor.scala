package ca.jrvs.spark

import org.apache.spark.sql.SparkSession

object Executor {

  // Create SparkSession with SQL Dataframes and Hive configuration
  var spark: SparkSession = _

  def apply(src: String, backup: String): Unit = {
    spark = sessionMaker.getSparkSession
    job(spark, src, backup)
  }

  def job(spark: SparkSession, src: String, backup: String) = {
    import spark.sql

    val df1 = sql("SELECT * FROM " + src + " USING hive")
    val df2 = sql("SELECT * FROM " + backup + " USING hive")

    // Get rows in backup that aren't in src
    val unarchived = df2.except(df1)

    // update backup to have the only the rows in src
    val result = df1.union(df2).except(unarchived)

  }
}
