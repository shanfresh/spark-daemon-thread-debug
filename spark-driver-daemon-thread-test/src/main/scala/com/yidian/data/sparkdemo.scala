package com.yidian.data

import java.util.concurrent.TimeUnit

import org.apache.spark.sql.SparkSession

/**
  * @author shanjixi on 19/8/18.
  */
object SparkDemo {
  def main(args: Array[String]): Unit = {
    val sparkSession = SparkSession.builder()
      .appName("AzkabanSpark2BasicTest")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.kryoserializer.buffer.max", "512m")
      .config("spark.rdd.compress", value = true)
      .enableHiveSupport()
      .getOrCreate()

    val ext = new SparkDemo(sparkSession).run()
  }
}

class SparkDemo(sparkSession: SparkSession) extends Serializable{
  def runThread()={
    val t = new Thread(new Runnable {
      override def run(): Unit = {
        while(true){
          println("Running something!")
          TimeUnit.SECONDS.sleep(1);
        }
      }
    })
    t.start();
  }
  def run():Unit ={
    runThread()
    val result = sparkSession.sql("select count(1) from dw.dim_new_user where p_day='2019-08-01' and p_type='app'").limit(1).take(1);
    result.foreach(row=>{
      println(row.toString())
    })
  }
}

