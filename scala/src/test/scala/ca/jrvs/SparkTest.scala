package ca.jrvs

import org.apache.spark.sql.SparkSession

import java.io.File
import org.scalatest.junit.AssertionsForJUnit
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.apache.commons.io.FileUtils;

class SparkTest extends AssertionsForJUnit {
  var spark: SparkSession = _

  @Before
  def setUp(): Unit = {
    spark = SparkSession
      .builder()
      .appName("Scala Query Test")
      .master("local[2]")
      .getOrCreate()
  }

  /***
   * Test Whether the spark query executes the right operation specified
   */
  @Test
  def test1(): Unit = {
    val df1 = spark.read.option("header", true).csv("data/src.csv")
    df1.show()
    val df2 = spark.read.option("header", true).csv("data/backup.csv")

    // Get rows in backup that aren't in src
    val unarchived = df2.except(df1)

    val result = df1.union(df2).except(unarchived)
    result
      .repartition(1)
      .orderBy("part")
      .write.format("com.databricks.spark.csv")
      .option("header", true)
      .save("output/output.csv")

    val expectedFile = new File("data/src.csv")
    val outFile = new File("output/output.csv")
    Assert.assertTrue("The files differ!", FileUtils.contentEquals(expectedFile, outFile))
  }
}