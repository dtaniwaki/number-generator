package com.github.dtaniwaki.number_generator.Num

import java.time.ZonedDateTime

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Random(maxNumber: Int, index: Int) extends Base {
  val r = new scala.util.Random(ZonedDateTime.now.toEpochSecond.toInt + index)
  override def generate(): Future[Map[String, Int]] = {
    val n = r.nextInt(maxNumber)
    Future { Map[String, Int](n.toString -> 1) }
  }
}
