package com.github.dtaniwaki.number_generator.Num

import java.time.ZonedDateTime

import com.github.dtaniwaki.number_generator.Client
import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Future

class External(host: String, port: Int) extends Base with StrictLogging {
  val r = new scala.util.Random(ZonedDateTime.now.toEpochSecond.toInt)
  val client = new Client(host, port)
  override def generate(): Future[Map[String, Int]] = {
    client.request()
  }
}
