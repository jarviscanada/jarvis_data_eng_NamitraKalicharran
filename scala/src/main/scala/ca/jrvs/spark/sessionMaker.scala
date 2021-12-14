package ca.jrvs.spark

import org.apache.spark.sql.SparkSession

object sessionMaker {
  private var name:String = "CIBC Spark"
  private var spark:SparkSession = SparkSession
    .builder()
    .appName(name)
    .master("local[2]") // Since I'm running in local mode
    //.config("spark.sql.warehouse.dir", warehouseLocation) // TODO: Specify where the Hive store is located
    .enableHiveSupport()
    .getOrCreate()

  def getSparkSession: SparkSession = spark
}
