package com.github.dtaniwaki.number_generator.Num

import java.net.{InetSocketAddress, Socket, InetAddress}
import java.time.ZonedDateTime

import com.typesafe.scalalogging.StrictLogging

import scala.concurrent.Future
import scala.io.BufferedSource

import spray.json._
import scala.concurrent.ExecutionContext.Implicits.global

class External(host: String, port: Int) extends Base with DefaultJsonProtocol with StrictLogging {
  val r = new scala.util.Random(ZonedDateTime.now.toEpochSecond.toInt)
  logger.info(s"Connecting ${host}:${port}...")
  override def generate(): Future[Map[String, Int]] = {
    Future {
      val socket = new Socket()
      try {
        socket.connect(new InetSocketAddress(InetAddress.getByName(host), port), 10)
        val body = new BufferedSource(socket.getInputStream()).getLines().mkString
        logger.debug(s"Retrieve: ${body}")
        body.parseJson.convertTo[Map[String, Int]]
      } finally {
        socket.close()
      }
    }
  }
}
